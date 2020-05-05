package br.com.contmatic.assembler;

import static br.com.contmatic.easy.random.classes.EnderecoEasyRandom.enderecoValido;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.easy.random.classes.EnderecoEasyRandom;
import br.com.contmatic.endereco.Endereco;

public class EnderecoAssemblyTest {

    Endereco endereco;
    Document documento;
    EnderecoAssembly assembler;

    @Before
    public void setUpBefore() {
        this.assembler = new EnderecoAssembly();
    }

    @Test
    public void deve_transformar_endereco_em_documento() {
        this.endereco = enderecoValido();
        assertThat(assembler.toDocument(endereco), instanceOf(Document.class));
    }

    @Test
    public void deve_transformar_o_documento_em_endereco() {
        this.endereco = EnderecoEasyRandom.enderecoValido();
        this.documento = Document.parse(endereco.toString());
        assertThat(assembler.toResource(documento), is(endereco));
    }

    @Test
    public void deve_retornar_endereco_nulo() {
        assertNull(assembler.toResource(documento));
    }

    @Test
    public void deve_retornar_documento_nulo() {
        assertNull(assembler.toDocument(endereco));
    }

}