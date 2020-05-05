package br.com.contmatic.assembler;

import static br.com.contmatic.easy.random.classes.EmpresaEasyRandom.empresaValida;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.empresa.Empresa;

public class EmpresaAssemblyTest {

    Empresa empresa;
    Document documento;
    EmpresaAssembly assembler;

    @Before
    public void setUpBefore() {
        this.assembler = new EmpresaAssembly();
    }

    @Test
    public void deve_transformar_o_empresa_em_documento() {
        this.empresa = empresaValida();
        assertThat(assembler.toDocument(empresa), instanceOf(Document.class));

    }

    @Test
    public void deve_transformar_o_documento_em_empresa() {
        this.empresa = empresaValida();
        this.documento = Document.parse(empresa.toString());
        assertThat(assembler.toResource(documento), is(empresa));
    }

    @Test
    public void deve_retornar_empresa_nula() {
        assertNull(assembler.toResource(documento));
    }

    @Test
    public void deve_retornar_documento_nulo() {
        assertNull(assembler.toDocument(empresa));
    }
}