package br.com.contmatic.assembler;

import static br.com.contmatic.easy.random.classes.FuncionarioEasyRandom.funcionarioValido;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.empresa.Funcionario;

public class FuncionarioAssemblyTest {
    
    Funcionario funcionario;
    Document documento;
    FuncionarioAssembly assembler;

    @Before
    public void setUpBefore() {
        this.assembler = new FuncionarioAssembly();
    }

    @Test
    public void deve_transformar_o_funcionario_em_documento() {
        this.funcionario = funcionarioValido();
        assertThat(assembler.toDocument(funcionario), instanceOf(Document.class));

    }

    @Test
    public void deve_transformar_o_documento_em_funcionario() {
        this.funcionario = funcionarioValido();
        this.documento = Document.parse(funcionario.toString());
        assertThat(assembler.toResource(documento), is(funcionario));
    }

    @Test
    public void deve_retornar_funcionario_nulo() {
        assertNull(assembler.toResource(documento));
    }
    
    @Test
    public void deve_retornar_documento_nulo() {
        assertNull(assembler.toDocument(funcionario));
    }

}
