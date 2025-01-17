package alura.desafio.literalura.model.libro;

import alura.desafio.literalura.model.autor.AutorDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignorar campos desconocidos
public record LibroDTO(
        @JsonAlias("title")
        String titulo,
        @JsonAlias("authors")
        List<AutorDTO> autor,  // Cambiar para recibir una lista de autores
        @JsonAlias("languages")
        List<String> idioma,
        @JsonAlias("copyright")
        Boolean copyrigth,
        @JsonAlias("download_count")
        int numDescargas
) {
}