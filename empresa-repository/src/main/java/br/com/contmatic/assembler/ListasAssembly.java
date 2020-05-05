package br.com.contmatic.assembler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;

public class ListasAssembly {

    public static Set<Telefone> toTelefones(List<Document> documents) {
        Set<Telefone> telefones = null;
        if (documents == null) {
            return telefones;
        }
        TelefoneAssembly telefone = new TelefoneAssembly();
        telefones = new HashSet<>();
        for(Document document : documents) {
            telefones.add(telefone.toResource(document));
        }
        return telefones;
    }

    public static Set<Endereco> toEnderecos(List<Document> documents) {
        Set<Endereco> enderecos = null;
        if (documents == null) {
            return enderecos;
        }
        EnderecoAssembly endereco = new EnderecoAssembly();
        enderecos = new HashSet<>();
        for(Document document : documents) {
            enderecos.add(endereco.toResource(document));
        }
        return enderecos;
    }

    public static Set<Cliente> toClientes(List<Document> documents) {
        Set<Cliente> clientes = null;
        if (documents == null) {
            return clientes;
        }
        ClienteAssembly cliente = new ClienteAssembly();
        clientes = new HashSet<>();
        for(Document document : documents) {
            clientes.add(cliente.toResource(document));
        }
        return clientes;
    }

    public static Set<Funcionario> toFuncionarios(List<Document> documents) {
        Set<Funcionario> funcionarios = null;
        if (documents == null) {
            return funcionarios;
        }
        FuncionarioAssembly funcionario = new FuncionarioAssembly();
        funcionarios = new HashSet<>();
        for(Document document : documents) {
            funcionarios.add(funcionario.toResource(document));
        }
        return funcionarios;
    }
    
    public static Set<Produto> toProdutos(List<Document> documents) {
        Set<Produto> produtos = null;
        if (documents == null) {
            return produtos;
        }
        ProdutoAssembly produto = new ProdutoAssembly();
        produtos = new HashSet<>();
        for(Document document : documents) {
            produtos.add(produto.toResource(document));
        }
        return produtos;
    }
}
