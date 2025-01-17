package alura.desafio.literalura.model.libro;

public record ListarLibroDTO(
        String titulo,
        String autor,
        String idioma,
        Boolean copyrigth,
        int numDescargas) {
    public ListarLibroDTO(Libro libro){
        this(libro.getTitulo(), libro.getAutor().getNombre(), libro.getIdioma().toString(), libro.getCopyrigth(), libro.getNumDescargas());
    }
    public String toString(){
        return """
                Titulo: %s
                Autor: %s
                Idioma: %s
                Copyrigth: %s
                Numero de descargas: %d
                """.formatted(titulo, autor, idioma, copyrigth, numDescargas);
    }


}
