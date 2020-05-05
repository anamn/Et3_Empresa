package br.com.contmatic.assembler;

import org.bson.Document;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.EnderecoType;

public class EnderecoAssembly implements Assembly<Endereco, Document> {

    @Override
    public Endereco toResource(Document document) {
        if (document != null) {
            Endereco endereco = new Endereco(document.getString("logradouro"), document.getString("bairro"), document.getString("numero"), document.getString("cep"),
                EnderecoType.valueOf(document.getString("tipo")));
            return endereco;
        }
        return null;
    }

    @Override 
    public Document toDocument(Endereco endereco) {
        if (endereco != null) {
            return Document.parse(endereco.toString());
        }
        return null;
    }
}
