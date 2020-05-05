package br.com.contmatic.assembler;

import static br.com.contmatic.easy.random.classes.TelefoneEasyRandom.telefoneValido;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.telefone.Telefone;

public class TelefoneAssemblyTest {

    Telefone telefone;
    Document documento;
    TelefoneAssembly assembler;

    @Before
    public void setUpBefore() {
        this.assembler = new TelefoneAssembly();
    }

    @Test
    public void deve_transformar_o_telefone_em_documento() {
        this.telefone = telefoneValido();
        assertThat(assembler.toDocument(telefone), instanceOf(Document.class));

    }

    @Test
    public void deve_transformar_o_documento_em_telefone() {
        this.telefone = telefoneValido();
        this.documento = Document.parse(telefone.toString());
        assertThat(assembler.toResource(documento), is(telefone));
    }

    @Test
    public void deve_retornar_telefone_nulo() {
        assertNull(assembler.toResource(documento));
    }

    @Test
    public void deve_retornar_documento_nulo() {
        assertNull(assembler.toDocument(telefone));
    }
}
