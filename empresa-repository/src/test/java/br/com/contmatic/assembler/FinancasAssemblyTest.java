package br.com.contmatic.assembler;

import static br.com.contmatic.easy.random.classes.FinancasEasyRandom.fincancaValida;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.financeiro.Financas;

public class FinancasAssemblyTest {

    Financas financas;
    Document documento;
    FinancasAssembly assembler;

    @Before
    public void setUpBefore() {
        this.assembler = new FinancasAssembly();
    }

    @Test
    public void deve_transformar_o_financas_em_documento() {
        this.financas = fincancaValida();
        assertThat(assembler.toDocument(financas), instanceOf(Document.class));

    }

    @Test
    public void deve_transformar_o_documento_em_financas() {
        this.financas = fincancaValida();
        this.documento = Document.parse(financas.toString());
        assertThat(assembler.toResource(documento), is(financas));
    }

    @Test
    public void deve_retornar_funcionario_nulo() {
        assertNull(assembler.toResource(documento));
    }

    @Test
    public void deve_retornar_documento_nulo() {
        assertNull(assembler.toDocument(financas));
    }
}