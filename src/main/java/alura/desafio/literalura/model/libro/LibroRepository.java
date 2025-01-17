package alura.desafio.literalura.model.libro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT l FROM Libro l WHERE l.idioma = :idiomaEnum")
    Optional<Libro> findAllByIdioma(Idioma idiomaEnum);

    Optional<Libro> findByTitulo(String titulo);

    @Query("""
            SELECT l FROM Libro l
            ORDER BY l.numDescargas DESC
            limit :i
            """)
    List<Libro> topLibrosMasDescargados(int i);
}
