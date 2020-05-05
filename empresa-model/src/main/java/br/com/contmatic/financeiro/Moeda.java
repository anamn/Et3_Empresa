package br.com.contmatic.financeiro;

public enum Moeda {
    
    REAL("Real"),
    EURO("Euro"),
    DOLLAR("Dollar");

    private String tipo;

    private Moeda(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {

        return this.getTipo();
    }

    public String getTipo() {
        return name();
    }
}