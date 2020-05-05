package br.com.contmatic.assembler;

import static br.com.contmatic.assembler.ListasAssembly.toTelefones;

import org.bson.Document;

import br.com.contmatic.empresa.Cliente;

public class ClienteAssembly implements Assembly<Cliente, Document> {

    @Override
    public Cliente toResource(Document document) {
        if (document != null) {
            EnderecoAssembly endereco = new EnderecoAssembly();
            Document documentEndereco = (Document) document.get("endereco");
            Cliente cliente = new Cliente(document.getString("nome"), document.getString("cpf"), toTelefones(document.getList("telefones", Document.class)), document.getString("email"),
                endereco.toResource(documentEndereco));
            return cliente;
        }
        return null;
    }

    @Override 
    public Document toDocument(Cliente cliente) {
        if (cliente != null) {
            return Document.parse(cliente.toString());
        }
        return null;
    }

}
