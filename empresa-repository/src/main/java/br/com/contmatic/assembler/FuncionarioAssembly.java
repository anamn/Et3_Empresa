package br.com.contmatic.assembler;

import static br.com.contmatic.assembler.ListasAssembly.toTelefones;

import java.math.BigDecimal;

import org.bson.Document;

import br.com.contmatic.empresa.Funcionario;

public class FuncionarioAssembly implements Assembly<Funcionario, Document> {

    @Override
    public Funcionario toResource(Document document) {
        if (document != null) {
            EnderecoAssembly endereco = new EnderecoAssembly();
            Document documentEndereco = (Document) document.get("endereco");
            Funcionario funcionario = new Funcionario(document.getString("nome"), document.getString("cpf"), document.getString("pis"), toTelefones(document.getList("telefones", Document.class)),
                new BigDecimal(document.getInteger("salario")), endereco.toResource(documentEndereco));
            return funcionario;
        }
        return null;
    }

    @Override
    public Document toDocument(Funcionario funcionario) {
        if (funcionario != null) {
            return Document.parse(funcionario.toString());
        }
        return null;
    }
}
