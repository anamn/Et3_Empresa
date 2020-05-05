package br.com.contmatic.repository;

public class Filtro {

    private String componente;
    private String valor;
    
    public Filtro(String componente, String valor) {
        this.componente = componente;
        this.valor = valor;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return componente + valor;
    }
    
    
}
