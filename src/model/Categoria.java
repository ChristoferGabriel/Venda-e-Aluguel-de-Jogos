package model;

public enum Categoria {
    ACAO,
    AVENTURA,
    RPG,
    ESPORTE;

    public static Categoria fromString(String texto) {

        texto = texto.toUpperCase()
                     .replace("Á", "A")
                     .replace("À", "A")
                     .replace("Ã", "A")
                     .replace("Â", "A")
                     .replace("É", "E")
                     .replace("Ê", "E")
                     .replace("Í", "I")
                     .replace("Ó", "O")
                     .replace("Ô", "O")
                     .replace("Õ", "O")
                     .replace("Ú", "U")
                     .replace("Ç", "C");

        return Categoria.valueOf(texto);
    }
}
