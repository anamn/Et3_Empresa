package br.com.contmatic.repository;

import java.util.List;

import br.com.contmatic.empresa.Empresa;

public class Filtro {

    private String componente;
    private String valor;

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

    public void verificaItens(Empresa empresa, List<String> pesquisa) {
        if (empresa.getCnpj() != null) {
            pesquisa.add("cnpj: " + empresa.getCnpj());
        }
        if (empresa.getClientes() != null) {
            pesquisa.add("clientes: " + empresa.getClientes());
        }
        if (empresa.getEmail() != null) {
            pesquisa.add("email: " + empresa.getEmail());
        }
        if (empresa.getEnderecos() != null) {
            pesquisa.add("enderecos: " + empresa.getEnderecos());
        }
        if (empresa.getFinancas() != null) {
            pesquisa.add("financas: " + empresa.getFinancas());
        }
        if (empresa.getFuncionarios() != null) {
            pesquisa.add("funcionarios: " + empresa.getFuncionarios());
        }
        if (empresa.getNome() != null) {
            pesquisa.add("nome: " + empresa.getNome());
        }
        if (empresa.getProdutos() != null) {
            pesquisa.add("produtos: " + empresa.getProdutos());
        }
        if (empresa.getSite() != null) {
            pesquisa.add("site: " + empresa.getSite());
        }
        if (empresa.getTelefones() != null) {
            pesquisa.add("site: " + empresa.getTelefones());
        }
    }

    @Override
    public String toString() {
        return componente + valor;
    }

}

