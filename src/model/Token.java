package model;

public class Token {


    public Tipos getTipo() {
        return tipo;
    }

    public void setTipo(Tipos tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    private Tipos tipo;
    private String valor;

    public enum Tipos {
        IDENTIFY("\\b(?!boolean|int|if|while|for|')[0-9]*[a-zA-Z_]+[0-9]*[a-zA-Z_]*(?!')\\b"),
        NUMCONST ("\\b(?![a-z]+)[0-9]+(?![a-z]+)\\b"),
        OPERATOR("[*|/|+|=|%|-]+"),
        SEPARATOR("[{|}|;|]"),
        CHARCONST("(')[a-z]+(')$"),
        KEYWORD ("(if|while|int|boolean|for)");

        public final String patron;

        Tipos(String s) {
            this.patron = s;
        }
    }

}
