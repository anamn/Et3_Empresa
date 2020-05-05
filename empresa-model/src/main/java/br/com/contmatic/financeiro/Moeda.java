package br.com.contmatic.financeiro;

public enum Moeda {

                   REAL("Real"),
                   EURO("Euro"),
                   DOLLAR("Dollar");

    private String tipo;

    private Moeda(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return name();
    }
}
