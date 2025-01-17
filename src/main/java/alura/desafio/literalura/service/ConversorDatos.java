package alura.desafio.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorDatos implements IConversorDatos{

        private ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper es una clase de Jackson que permite convertir JSON a objetos Java y viceversa

        @Override
        public <T> T obtenerDatos(String json, Class<T> clase) {
            try {
                return objectMapper.readValue(json, clase);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

}
