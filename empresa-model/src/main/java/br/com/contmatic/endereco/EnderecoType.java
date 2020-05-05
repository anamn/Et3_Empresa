package br.com.contmatic.endereco;

public enum EnderecoType {

                          CASA("Casa"),
                          APARTAMENTO("Apartamento"),
                          COMERCIAL("Comercial");

    private String tipo;

    private EnderecoType(String tipo) {
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
