package br.com.contmatic.assembler;

import java.math.BigDecimal;

import org.bson.Document;

import br.com.contmatic.empresa.Produto;

public class ProdutoAssembly implements Assembly<Produto, Document> {

    @Override
    public Produto toResource(Document document) {
        if (document != null) {
            Produto produto = new Produto(document.getString("tipo"), document.getString("descricao"), new BigDecimal(document.getInteger("preco")), document.getString("codigo"),
                document.getInteger("quantidade"));
            return produto;
        }
        return null;
    }

    @Override
    public Document toDocument(Produto produto) {
        if (produto != null) {
            return Document.parse(produto.toString());
        }
        return null;
    }

}
