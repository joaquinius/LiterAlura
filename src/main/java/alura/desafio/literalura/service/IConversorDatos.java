package alura.desafio.literalura.service;

public interface IConversorDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
