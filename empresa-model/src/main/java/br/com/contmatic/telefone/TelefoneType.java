package br.com.contmatic.telefone;

public enum TelefoneType {

                          CELULAR("Celular"),
                          FIXO("Fixo");

    private String tipo;

    private TelefoneType(String tipo) {
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
