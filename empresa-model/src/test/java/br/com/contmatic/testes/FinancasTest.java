package br.com.contmatic.testes;

import static br.com.contmatic.beanValidation.ValidationFinancas.validador;
import static br.com.contmatic.easy.random.classes.FinancasEasyRandomParametros.parametrosLucro;
import static br.com.contmatic.financeiro.Moeda.DOLLAR;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import org.hamcrest.Matchers;
import org.jeasy.random.EasyRandom;
import org.joda.time.YearMonth;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.financeiro.Financas;
import br.com.contmatic.grupos.GrupoData;
import br.com.contmatic.grupos.GrupoValores;
import nl.jqno.equalsverifier.EqualsVerifier;

public class FinancasTest {

    private Financas lucro = null;

    @Before
    public void setUpBefore() {
        this.lucro = new EasyRandom(parametrosLucro()).nextObject(Financas.class);
    }

    @After
    public void setDownAfter() {
        this.lucro = null;
    }

    // Testes atributos
    @Test
    public void nao_deve_retornar_erros_de_validacao_nos_atributos_de_valores() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(lucro, GrupoValores.class)));
    }

    @Test
    public void nao_deve_retornar_erros_de_validacao_nos_atributo_de_data() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(lucro, GrupoData.class)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_investimento_menor_que_zero() {
        lucro.setInvestimento(new BigDecimal("-450"));
        validador(lucro, GrupoValores.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_investimento_igual_a_zero() {
        lucro.setInvestimento(new BigDecimal("0"));
        validador(lucro, GrupoValores.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_investimento_nulo() {
        lucro.setInvestimento(null);
        validador(lucro, GrupoValores.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_na_renda_menor_que_zero() {
        lucro.setRenda(new BigDecimal("-450"));
        validador(lucro, GrupoValores.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_na_renda_igual_a_zero() {
        lucro.setRenda(new BigDecimal("0"));
        validador(lucro, GrupoValores.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_na_renda_nula() {
        lucro.setRenda(null);
        validador(lucro, GrupoValores.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_mes_futuro() {
        lucro.setMes(new YearMonth(2020, DECEMBER.getValue()));
        validador(lucro, GrupoData.class);
    }

    // Testa metodo
    @Test
    public void deve_retornar_o_lucro_calculado_em_moeda() {
        assertThat(lucro.getRenda().subtract(lucro.getInvestimento()), Matchers.is(lucro.calculaLucro()));
    }

    // Teste Construtor
    @Test
    public void deve_retornar_valores() {
        lucro.setInvestimento(new BigDecimal("33000"));
        assertTrue(lucro.getInvestimento().equals(new BigDecimal("33000")));
        lucro.setRenda(new BigDecimal("33000"));
        assertTrue(lucro.getRenda().equals(new BigDecimal("33000")));
        lucro.setMes(new YearMonth(2019, AUGUST.getValue()));
        assertTrue(lucro.getMes().equals(new YearMonth(2019, AUGUST.getValue())));
        lucro.setMoeda(DOLLAR);
        assertTrue(lucro.getMoeda().equals(DOLLAR));
    }

    // Teste hashCode, Equals e toString
    @Test
    public void deve_testar_equals_e_hashCode() {
        EqualsVerifier.forClass(Financas.class).suppress(NONFINAL_FIELDS).withIgnoredFields("investimento").withIgnoredFields("renda").withIgnoredFields("moeda").verify();
    }

    @Test
    public void deve_verificar_se_toString_contem_investimento() {
        assertTrue(lucro.toString().contains("investimento"));
    }

    @Test
    public void deve_verificar_se_toString_contem_renda() {
        assertTrue(lucro.toString().contains("renda"));
    }

    @Test
    public void deve_verificar_se_toString_contem_moeda() {
        assertTrue(lucro.toString().contains("moeda"));
    }

    @Test
    public void deve_verificar_se_toString_contem_mes() {
        assertTrue(lucro.toString().contains("mes"));
    }
}
