package alura.desafio.literalura.model.libro;

import java.util.HashMap;
import java.util.Map;

public enum Idioma {
    ESPANOL("es"),
    INGLES("en"),
    FRANCES("fr"),
    ITALIANO("it"),
    ALEMAN("de"),
    PORTUGUES("pt"),
    RUSO("ru"),
    CHINO("zh"),
    JAPONES("ja");

    private final String codigo;


    private static final Map<String, Idioma> CODIGO_A_IDIOMA = new HashMap<>();


    static {
        for (Idioma idioma : values()) {
            CODIGO_A_IDIOMA.put(idioma.codigo, idioma);
        }
    }


    Idioma(String codigo) {
        this.codigo = codigo;
    }


    public static Idioma desdeCodigo(String codigo) {
        return CODIGO_A_IDIOMA.getOrDefault(codigo, null);
    }


    public String getCodigo() {
        return codigo;
    }
}