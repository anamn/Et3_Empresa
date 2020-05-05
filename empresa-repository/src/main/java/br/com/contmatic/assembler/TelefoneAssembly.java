package br.com.contmatic.assembler;

import org.bson.Document;

import br.com.contmatic.telefone.Ddd;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneType;

public class TelefoneAssembly implements Assembly<Telefone, Document> {

    @Override
    public Telefone toResource(Document document) {
        if (document != null) {
            Telefone telefone = new Telefone(Ddd.valueOf(document.getString("ddd")), document.getString("numero"), TelefoneType.valueOf(document.getString("tipo")));
            return telefone;
        }
        return null;
    }

    @Override
    public Document toDocument(Telefone telefone) {
        if (telefone != null) {
            return Document.parse(telefone.toString());
        }
        return null;
    }
}
