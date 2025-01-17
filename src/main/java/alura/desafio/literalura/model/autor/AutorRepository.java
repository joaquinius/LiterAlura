package alura.desafio.literalura.model.autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombreAutor);

    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :anioInt AND (a.fechaMuerte IS NULL OR a.fechaMuerte >= :anioInt)")
    List<Autor> buscarPorIntervaloTiempo(int anioInt);
}