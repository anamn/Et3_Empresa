package br.com.contmatic.assembler;

import static br.com.contmatic.easy.random.classes.ProdutoEasyRandom.produtoValido;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.empresa.Produto;

public class ProdutoAssemblyTest {

    Produto produto;
    Document documento;
    ProdutoAssembly assembler;

    @Before
    public void setUpBefore() {
        this.assembler = new ProdutoAssembly();
    }

    @Test
    public void deve_transformar_o_produto_em_documento() {
        this.produto = produtoValido();
        assertThat(assembler.toDocument(produto), instanceOf(Document.class));

    }

    @Test
    public void deve_transformar_o_documento_em_produto() {
        this.produto = produtoValido();
        this.documento = Document.parse(produto.toString());
        assertThat(assembler.toResource(documento), is(produto));
    }

    @Test
    public void deve_retornar_produto_nulo() {
        assertNull(assembler.toResource(documento));
    }
    
    @Test
    public void deve_retornar_documento_nulo() {
        assertNull(assembler.toDocument(produto));
    }
}
