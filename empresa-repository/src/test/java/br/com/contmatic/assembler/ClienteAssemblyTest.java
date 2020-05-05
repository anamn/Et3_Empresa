package br.com.contmatic.assembler;

import static br.com.contmatic.easy.random.classes.ClienteEasyRandom.clienteValido;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.empresa.Cliente;

public class ClienteAssemblyTest {

    Cliente cliente;
    Document documento;
    ClienteAssembly assembler;

    @Before
    public void setUpBefore() {
        this.assembler = new ClienteAssembly();
    }

    @Test
    public void deve_transformar_o_cliente_em_documento() {
        this.cliente = clienteValido();
        assertThat(assembler.toDocument(cliente), instanceOf(Document.class));

    }

    @Test
    public void deve_transformar_o_documento_em_cliente() {
        this.cliente = clienteValido();
        this.documento = Document.parse(cliente.toString());
        assertThat(assembler.toResource(documento), is(cliente));
    }

    @Test
    public void deve_retornar_cliente_nulo() {
        assertNull(assembler.toResource(documento));
    }
    
    @Test
    public void deve_retornar_documento_nulo() {
        assertNull(assembler.toDocument(cliente));
    }

}