package alura.desafio.literalura.principal;
import alura.desafio.literalura.model.autor.Autor;
import alura.desafio.literalura.model.autor.AutorRepository;
import alura.desafio.literalura.model.libro.*;

import alura.desafio.literalura.service.ConsumoAPI;
import alura.desafio.literalura.service.ConversorDatos;
import alura.desafio.literalura.service.RespuestaAPI;
import alura.desafio.literalura.model.autor.ListarAutorDTO;


import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URLBase = "https://gutendex.com/books/?search=";
    private ConversorDatos conversorDatos = new ConversorDatos();

    private LibroRepository libroRepository;

    private AutorRepository autorRepository;
    private int anioInt;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    private void limpiarBuffer() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }


    public void menu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu_principal = """
                    ============================
                    Bienvenido a LeterAlura
                    ============================
                    1.- Buscar libro por titulo
                    2.- Listar libros registrados
                    3.- Listar autores registrados
                    4.- Listar autores vivos en un determinado año
                    5.- Listar libros por idioma
                    6.- Top libros más descargados
                    0.- Salir
                    ============================
                    """;
            System.out.println(menu_principal);
            System.out.println("Ingrese una opcion: ");
            var opcion2 = scanner.next();
            if (!validarOpcion(opcion2)) {
                continue;
            }
            opcion = Integer.parseInt(opcion2);
            System.out.println("============================");
            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 6:
                    topLibrosMasDescargados();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }

    private boolean validarOpcion(String opcion) {
        try {
            Integer.parseInt(opcion);
        } catch (NumberFormatException e) {
            System.out.println("Opcion invalida");
            return false;
        }
        return true;
    }

    private List<LibroDTO> obtenerLibros() {
        while(true){
            System.out.println("Ingrese el título del libro: ");
            limpiarBuffer();
            var titulo = scanner.nextLine();

            var url = URLBase + (titulo.trim().toLowerCase().replace(" ", "+"));
            System.out.println(url);
            var json = consumoAPI.obtenerDatos(url);

            // Deserializar a RespuestaAPI
            RespuestaAPI respuesta = conversorDatos.obtenerDatos(json, RespuestaAPI.class);

            // Obtener la lista de resultados
            List<LibroDTO> libros = respuesta.getResults();

            // Verificar si no se encontraron libros
            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros con el título proporcionado.");
                System.out.println("... Vuelva a intentarlo");
                continue; // Retorna una lista vacía si no se encontraron libros
            }
            // Devolver la lista de libros
            return libros;
        }

    }

    private void registrarLibro(List<LibroDTO> libros) {
        while (true) {
            System.out.println("Libros encontrados:");

            for (int i = 0; i < libros.size(); i++) {
                System.out.println((i + 1) + ".- " + libros.get(i).titulo());
            }

            System.out.println("Ingrese el número del libro que desea registrar (0 para salir):");
            var opcion1 = scanner.nextLine().trim();

            if (!validarOpcion(opcion1)) {
                continue;
            }

            var opcion2 = Integer.parseInt(opcion1);

            if (opcion2 == 0) {
                return;
            }

            if (opcion2 < 0 || opcion2 > libros.size()) {
                System.out.println("Opción inválida");
                continue;
            }

            var libroDTO = libros.get(opcion2 - 1);

            // Buscar si el libro ya está registrado
            var libroRegistrado = libroRepository.findByTitulo(libroDTO.titulo()).orElse(null);
            if(libroRegistrado!=null){
                System.out.println("El libro ya está registrado");
                continue;
            }

            // Buscar o crear autor
            Autor autor = null;
            if (libroDTO.autor() != null && !libroDTO.autor().isEmpty()) {
                var nombreAutor = libroDTO.autor().get(0).nombre();
                autor = autorRepository.findByNombre(nombreAutor).orElse(null);

                if (autor == null) {
                    // Crear y guardar nuevo autor si no existe
                    autor = new Autor(libroDTO.autor().get(0));
                    autor = autorRepository.save(autor);
                }
            }

            var libro = new Libro(libroDTO);
            if (autor != null) {
                libro.setAutor(autor);
            }

            libroRepository.save(libro);
            System.out.println("Libro registrado");
            return;
        }
    }

    private void buscarLibro() {
        var libros = obtenerLibros();
        registrarLibro(libros);
    }

    private void listarLibros() {
        var libros = libroRepository.findAll();

        System.out.println("Libros registrados:");
        var librosListar = libros.stream().map(ListarLibroDTO::new).toList();

        librosListar.stream().forEach(System.out::println);
    }

    private void listarAutores() {
        var autores = autorRepository.findAll();

        System.out.println("Autores registrados:");
        var autoresListar = autores.stream().map(ListarAutorDTO::new).toList();

        autoresListar.stream().forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        while (true) {
            System.out.println("Ingrese el año (Digite 0 para cancelar):");
            var anio = scanner.nextLine().trim();

            if (anio.isEmpty() || !validarOpcion(anio)) {
                System.out.println("Digite un año válido, no letras ni campos vacíos");
                continue;
            }

            var anioInt = Integer.parseInt(anio);

            if (anioInt == 0) {
                return;
            }

            if (anioInt < 0 || anioInt > LocalDate.now().getYear()) {
                System.out.println("Año inválido");
                continue;
            }
            var autores = autorRepository.buscarPorIntervaloTiempo(anioInt);

            // Mostrar autores encontrados
            System.out.println("Autores vivos en el año " + anioInt + ":");
            if (autores.isEmpty()) {
                System.out.println("No se encontraron autores vivos para el año " + anioInt);
                continue;
            } else {
                var autoresListar = autores.stream()
                        .map(ListarAutorDTO::new)
                        .toList();
                autoresListar.forEach(System.out::println);
            }
        }
    }


    private void listarLibrosPorIdioma(){
        while(true){
            System.out.println("Idiomas disponibles:");
            for (var idioma : Idioma.values()) {
                System.out.println(idioma.getCodigo() + ".- " + idioma.name());
            }
            System.out.println("Ingrese el código del idioma (X para salir):");
            var idioma = scanner.nextLine().trim();
            if(idioma.equalsIgnoreCase("X")){
                return;
            }
            var idiomaEnum = Idioma.desdeCodigo(idioma);
            if(idiomaEnum == null){
                System.out.println("Idioma inválido");
                continue;
            }
            var libros = libroRepository.findAllByIdioma(idiomaEnum);
            System.out.println("Libros en " + idiomaEnum.name() + ":");
            var librosListar = libros.stream().map(ListarLibroDTO::new).toList();
            librosListar.stream().forEach(System.out::println);
        }
    }

    private void topLibrosMasDescargados() {
        System.out.println("Ingrese el número de libros que desea ver:");
        limpiarBuffer();
        var numLibros = scanner.nextLine();

        if (!validarOpcion(numLibros)) {
            return;
        }

        if (Integer.parseInt(numLibros) <= 0) {
            System.out.println("Número inválido");
            return;
        }

        var libros = libroRepository.topLibrosMasDescargados(Integer.parseInt(numLibros));

        System.out.println("Top " + numLibros + " libros más descargados:");
        var librosListar = libros.stream()
                .map(ListarLibroDTO::new)
                .toList();
        librosListar.forEach(System.out::println);
    }


}
