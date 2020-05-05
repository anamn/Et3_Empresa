package br.com.contmatic.testes;

import static br.com.contmatic.beanValidation.ValidationTelefone.validador;
import static br.com.contmatic.easy.random.classes.TelefoneEasyRandom.parametrosTelefone;
import static br.com.contmatic.telefone.Ddd.PR44;
import static br.com.contmatic.telefone.TelefoneType.CELULAR;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.TreeSet;

import org.jeasy.random.EasyRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.grupos.GrupoIdentificacao;
import br.com.contmatic.telefone.Telefone;
import nl.jqno.equalsverifier.EqualsVerifier;

public class TelefoneTest {

    private Telefone telefone;
    
    @Before
    public void setUpBefore() {
        this.telefone = new EasyRandom(parametrosTelefone()).nextObject(Telefone.class);
    }

    @After
    public void setDownAfter() {
        this.telefone = null;
    }

    // Testes atributos
    @Test
    public void nao_deve_retornar_mensagem_de_erro() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(telefone, GrupoIdentificacao.class)));

    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_numero_pelo_tamanho_maior() {
        telefone.setNumero(randomNumeric(10));
        validador(telefone, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_por_nao_aceitar_numero_com_nove_digitos_que_nao_comece_com_9() {
        telefone.setNumero("5" + randomNumeric(8));
        validador(telefone, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_numero_pelo_tamanho_menor() {
        telefone.setNumero(randomNumeric(7));
        validador(telefone, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_nao_deve_aceitar_letras_no_numero() {
        telefone.setNumero("ani90908");
        validador(telefone, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_nao_deve_aceitar_caractere_especial_no_numero() {
        telefone.setNumero("9-_*0908");
        validador(telefone, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_numero_nulo() {
        telefone.setNumero(null);
        validador(telefone, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_ddd_nulo() {
        telefone.setDdd(null);
        validador(telefone, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_tipo_nulo() {
        telefone.setTipo(null);
        validador(telefone, GrupoIdentificacao.class);
    }

    // Teste Construtor
    @Test
    public void deve_retornar_valores() {
        telefone.setNumero("21 965452344");
        assertTrue(telefone.getNumero().equals("21 965452344"));
        telefone.setTipo(CELULAR);
        assertThat(telefone.getTipo().getTipo(), is(CELULAR.getTipo()));
        telefone.setDdd(PR44);
        assertThat(telefone.getDdd().toString(), is(PR44.toString()));
    }

    // Testes HashCode, Equals e toString
    @Test
    public void deve_testar_equals_e_hashCode() {
        EqualsVerifier.forClass(Telefone.class).suppress(NONFINAL_FIELDS).withIgnoredFields("tipo").verify();
    }

    @Test
    public void deve_verificar_se_toString_contem_ddd() {
        assertTrue(telefone.toString().contains("ddd"));
    }

    @Test
    public void deve_verificar_se_toString_contem_numero() {
        assertTrue(telefone.toString().contains("numero"));
    }

    @Test
    public void deve_verificar_se_toString_contem_tipo() {
        assertTrue(telefone.toString().contains("tipo"));
    }
}
