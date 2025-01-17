package alura.desafio.literalura.model.autor;

public record ListarAutorDTO(
        String nombre,
        String fechaNacimiento,
        String fechaMuerte
) {
    public ListarAutorDTO(Autor autor){
        this(autor.getNombre(), autor.getFechaNacimiento(), autor.getFechaMuerte());
    }
    public String toString(){
        return """
                Nombre: %s
                Fecha de nacimiento: %s
                Fecha de defunción: %s
                """.formatted(nombre, fechaNacimiento, fechaMuerte);
    }
}
