# LiterAlura
¿Que es LiterAlura?
LiterAlura es una aplicacion de consola hecha en java 17 
## Funcionalidades

La aplicación cuenta con un menú interactivo que permite a los usuarios realizar las siguientes acciones:

1. **Buscar libro por título**: Permite buscar libros por título utilizando la API de Gutendex y registrar el libro si se desea.
2. **Listar libros registrados**: Muestra una lista de todos los libros que han sido registrados en el sistema.
3. **Listar autores registrados**: Muestra una lista de todos los autores registrados en el sistema.
4. **Listar autores vivos en un determinado año**: Permite ingresar un año y obtener los autores que estaban vivos en ese año.
5. **Listar libros por idioma**: Muestra todos los libros registrados en el sistema de un idioma específico.
6. **Top libros más descargados**: Muestra los libros más descargados del sistema, según el número de descargas.

## Requisitos
- **JDK 17 o superior**:
- - **Base de datos PostgreSQL**
- **Dependencias de Maven**

## Dependencias
**Spring Boot Starter Data JPA**
**PostgreSQL Driver**:
**Spring Boot Starter Test**:
 **Jackson Databind**:

 ## Como usar 
 1. **Clonar el repositorio o descargarlo **
 2. ```bash
    https://github.com/joaquinius/LiterAlura
      ```
3. usar tu editor de codigo o IDE favorite
4. configurar la base de datos ya sea postgres como se utilizo en este proyecto o el de tu preferencia
   ```properties
    spring.application.name=literalura
    spring.datasource.url=jdbc:postgresql://${DB_Host}/${DB_Name} // debe reemplazarse con postgres y el nombre de la base de datos
    spring.datasource.username=${DB_User} // nombre de usuario del metor de base de datos
    spring.datasource.password=${DB_Password} // contraseña
    spring.datasource.driver-class-name=org.postgresql.Driver
    hibernate.dialect=org.hibernate.dialect.HSQLDialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql = true
    spring.jpa.format-sql = true
   ```
