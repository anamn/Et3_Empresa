package br.com.contmatic.testes;

import static br.com.contmatic.beanValidation.ValidationEndereco.validador;
import static br.com.contmatic.easy.random.classes.EnderecoEasyRandom.parametrosEndereco;
import static br.com.contmatic.endereco.EnderecoType.APARTAMENTO;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.Set;
import java.util.TreeSet;

import org.jeasy.random.EasyRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.grupos.GrupoIdentificacao;
import nl.jqno.equalsverifier.EqualsVerifier;

@FixMethodOrder(NAME_ASCENDING)
public class EnderecoTest {

    private Endereco endereco;

    @Before
    public void setUpBefore() {
        this.endereco = new EasyRandom(parametrosEndereco()).nextObject(Endereco.class);
    }

    @After
    public void setDownAfter() {
        this.endereco = null;
    }

    // Testa atributos
    @Test
    public void nao_deve_retornar_erros_de_validacao() {
        Set<String> teste = new TreeSet<String>();
        System.out.println(endereco);
        assertThat(teste, is(validador(endereco, GrupoIdentificacao.class)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_logradouro_null() {
        endereco.setLogradouro(null);
        validador(endereco, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_logradouro_com_numero() {
        endereco.setLogradouro("Rua 8789");
        validador(endereco, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_logradouro_com_caracter_especial() {
        endereco.setLogradouro("R-a j!!");
        validador(endereco, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_logradouro_pelo_tamanho_maior() {
        endereco.setLogradouro(randomAlphabetic(52));
        validador(endereco, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_logradouro_pelo_tamanho_menor() {
        endereco.setLogradouro(randomAlphabetic(1));
        validador(endereco, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_bairro_null() {
        endereco.setBairro(null);
        validador(endereco, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_bairro_com_numero() {
        endereco.setBairro("Bairro 8789");
        validador(endereco, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_bairro_com_caracter_especial() {
        endereco.setBairro("V!l@ j!!");
        validador(endereco, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_bairro_pelo_tamanho_maior() {
        endereco.setBairro(randomAlphabetic(41));
        validador(endereco, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_bairro_pelo_tamanho_menor() {
        endereco.setBairro(randomAlphabetic(1));
        validador(endereco, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_numero_pelo_tamanho_maior() {
        endereco.setNumero(randomNumeric(9, 20));
        validador(endereco, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_numero_igual_a_zero() {
        endereco.setNumero("0");
        validador(endereco, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_numero_null() {
        endereco.setNumero(null);
        validador(endereco, GrupoIdentificacao.class);
    }

    @Test
    public void deve_aceitar_cep_com_traco() {
        Set<String> teste = new TreeSet<String>();
        endereco.setCep("03251-070");
        assertThat(teste, is(validador(endereco, GrupoIdentificacao.class)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cep_pelo_tamanho_maior() {
        endereco.setCep(randomNumeric(9, 15));
        validador(endereco, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cep_pelo_tamanho_menor() {
        endereco.setCep(randomNumeric(2, 7));
        validador(endereco, GrupoIdentificacao.class);
    }

    // Testa Construtor
    @Test
    public void deve_retornar_os_valores() {
        endereco.setLogradouro("Av. ac");
        assertThat(endereco.getLogradouro(), is("Av. ac"));
        endereco.setBairro("Vila diva");
        assertThat(endereco.getBairro(), is("Vila diva"));
        endereco.setCep("19263720");
        assertThat(endereco.getCep(), is("19263720"));
        endereco.setNumero("187");
        assertThat(endereco.getNumero(), is("187"));
        endereco.setEnderecoType(APARTAMENTO);
        assertThat(endereco.getEnderecoType(), is(APARTAMENTO));
    }

    // Teste hashCode, Equals e toString
    @Test
    public void deve_testar_equals_e_hashCode() {
    EqualsVerifier.forClass(Endereco.class).withIgnoredFields("enderecoType").suppress(NONFINAL_FIELDS).verify();
    }

    @Test
    public void deve_verificar_se_toString_contem_logradouro() {
        assertTrue(endereco.toString().contains("logradouro"));
    }

    @Test
    public void deve_verificar_se_toString_contem_bairro() {
        assertTrue(endereco.toString().contains("bairro"));
    }

    @Test
    public void deve_verificar_se_toString_contem_numero() {
        assertTrue(endereco.toString().contains("numero"));
    }

    @Test
    public void deve_verificar_se_toString_contem_cep() {
        assertTrue(endereco.toString().contains("cep"));
    }

    @Test
    public void deve_verificar_se_toString_contem_enderecoType() {
        assertTrue(endereco.toString().contains("enderecoType"));
    }
}
