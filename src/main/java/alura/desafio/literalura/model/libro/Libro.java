package alura.desafio.literalura.model.libro;

import alura.desafio.literalura.model.autor.Autor;
import jakarta.persistence.*;

@Entity(name = "Libro")
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne(optional = true)
    @JoinColumn(name = "autor_id", referencedColumnName = "id", nullable = true)
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private Boolean copyrigth;

    private int numDescargas;

    public Libro() {
    }

    public Libro(Long id, String titulo, Autor autor, Idioma idioma, Boolean copyrigth, int numDescargas) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.copyrigth = copyrigth;
        this.numDescargas = numDescargas;
    }

    public Libro(LibroDTO libroDTO) {
        this.titulo = libroDTO.titulo();
        if (libroDTO.autor() != null && !libroDTO.autor().isEmpty()) {
            this.autor = new Autor(libroDTO.autor().get(0)); // Tomar el primer autor de la lista
        }
        this.idioma = Idioma.desdeCodigo(libroDTO.idioma().get(0));
        this.copyrigth = libroDTO.copyrigth();
        this.numDescargas = libroDTO.numDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Boolean getCopyrigth() {
        return copyrigth;
    }

    public void setCopyrigth(Boolean copyrigth) {
        this.copyrigth = copyrigth;
    }

    public int getNumDescargas() {
        return numDescargas;
    }

    public void setNumDescargas(int numDescargas) {
        this.numDescargas = numDescargas;
    }
}