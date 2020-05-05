package br.com.contmatic.assembler;

import static br.com.contmatic.assembler.ListasAssembly.toClientes;
import static br.com.contmatic.assembler.ListasAssembly.toTelefones;

import org.bson.Document;

import br.com.contmatic.empresa.Empresa;

public class EmpresaAssembly implements Assembly<Empresa, Document> {

    @Override
    public Empresa toResource(Document document) {
        if (document != null) {
            Empresa empresa = new Empresa(document.getString("nome"), document.getString("cnpj"), toTelefones(document.getList("telefones", Document.class)), document.getString("email"),
                ListasAssembly.toEnderecos(document.getList("enderecos", Document.class)));
            empresa.setSite(document.getString("site"));
            empresa.setClientes(toClientes(document.getList("clientes", Document.class)));
            empresa.setFuncionarios(ListasAssembly.toFuncionarios(document.getList("funcionarios", Document.class)));
            empresa.setProdutos(ListasAssembly.toProdutos(document.getList("produtos", Document.class)));
            FinancasAssembly financas= new FinancasAssembly();
            Document documentFinancas = (Document) document.get("financas");
            empresa.setFinancas(financas.toResource(documentFinancas));
            return empresa;
        }
        return null;
    }

    @Override
    public Document toDocument(Empresa empresa) {
        if (empresa != null) {
            return Document.parse(empresa.toString());
        }
        return null;
    }
}

