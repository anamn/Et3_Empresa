package br.com.contmatic.assembler;

import java.math.BigDecimal;

import org.bson.Document;
import org.joda.time.YearMonth;

import br.com.contmatic.financeiro.Financas;
import br.com.contmatic.financeiro.Moeda;

public class FinancasAssembly implements Assembly<Financas, Document> {

    @Override
    public Financas toResource(Document document) {
        if (document != null) {
            String data = document.getString("mes");
            String anoString = ("" + data.charAt(0)).concat(("" + data.charAt(1))).concat(("" + data.charAt(2))).concat(("" + data.charAt(3)));
            int ano = Integer.parseInt(anoString);
            String mesString = ("" + data.charAt(5)).concat("" + data.charAt(6));
            int mes = Integer.parseInt(mesString);
            Financas financas = new Financas(new BigDecimal(document.getInteger("investimento")), new BigDecimal(document.getInteger("renda")), Moeda.valueOf(document.getString("moeda")),
                new YearMonth(ano, mes));
            return financas;
        }
        return null;
    }

    @Override
    public Document toDocument(Financas financas) {
        if (financas != null) {
            return Document.parse(financas.toString());
        }
        return null;
    }
}
