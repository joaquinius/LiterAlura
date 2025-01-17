package alura.desafio.literalura.model.autor;

import jakarta.persistence.*;

@Entity(name = "Autor")
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String fechaNacimiento;

    private String fechaMuerte;

    public Autor() {
    }

    public Autor(Long id, String nombre, String fechaNacimiento, String fechaMuerte) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaMuerte = fechaMuerte;
    }

    public Autor(AutorDTO autorDTO) {
        this.nombre = autorDTO.nombre();
        this.fechaNacimiento = autorDTO.fechaNacimiento();
        this.fechaMuerte = autorDTO.fechaMuerte();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(String fechaDefuncion) {
        this.fechaMuerte = fechaDefuncion;
    }


}
