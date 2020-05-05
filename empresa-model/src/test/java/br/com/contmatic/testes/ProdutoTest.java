package br.com.contmatic.testes;

import static br.com.contmatic.beanValidation.ValidationProduto.validador;
import static br.com.contmatic.easy.random.classes.ProdutoEasyRandom.parametrosProduto;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import org.jeasy.random.EasyRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.empresa.Produto;
import br.com.contmatic.grupos.GrupoIdentificacao;
import br.com.contmatic.grupos.GrupoValores;
import nl.jqno.equalsverifier.EqualsVerifier;

@FixMethodOrder(NAME_ASCENDING)
public class ProdutoTest {

    private Produto produto;

    private Produto produto1;

    @Before
    public void setUpBefore() {
        this.produto = new EasyRandom(parametrosProduto()).nextObject(Produto.class);
        this.produto1 = new EasyRandom(parametrosProduto()).nextObject(Produto.class);
    }

    @After
    public void setDownAfter() {
        this.produto = null;
        this.produto1 = null;
    }

    // Testes atributos
    @Test
    public void nao_deve_retornar_erros_da_validacao_nos_atributos_de_identificacao() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(produto, GrupoIdentificacao.class)));
    }

    @Test
    public void nao_deve_retornar_erros_da_validacao_nos_atributos_de_valores() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(produto, GrupoValores.class)));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_codigo_pelo_tamanho_maior() {
        produto.setCodigo(randomNumeric(51));
        validador(produto, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_codigo_pelo_tamanho_menor() {
        produto.setCodigo(randomNumeric(4));
        validador(produto, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_codigo_nulo() {
        produto.setCodigo(null);
        validador(produto, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_descricao_tamanho_menor() {
        produto.setDescricao(randomAlphabetic(9));
        validador(produto, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_descricao_tamanho_maior() {
        produto.setDescricao(randomAlphabetic(61));
        validador(produto, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_descricao_nula() {
        produto.setDescricao(null);
        validador(produto, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_preco_menor_que_zero() {
        produto.setPreco(new BigDecimal("-50"));
        validador(produto, GrupoValores.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_preco_igual_a_zero() {
        produto.setPreco(new BigDecimal("0"));
        validador(produto, GrupoValores.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_preco_nulo() {
        produto.setPreco(null);
        validador(produto, GrupoValores.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_quantidade_igual_a_zero() {
        produto.setQuantidade(0);
        validador(produto, GrupoValores.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_quantidade_menor_que_zero() {
        produto.setQuantidade(-9);
        validador(produto, GrupoValores.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_na_quantidade_nula() {
        produto.setQuantidade(null);
        validador(produto, GrupoValores.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_tipo_tamanho_menor() {
        produto.setTipo(randomAlphabetic(1));
        validador(produto, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_tipo_tamanho_maior() {
        produto.setTipo(randomAlphabetic(51));
        validador(produto, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_tipo_nulo() {
        produto.setTipo(null);
        validador(produto, GrupoIdentificacao.class);
    }

    // Teste Construtor
    @Test
    public void deve_retornar_valores() {
        produto.setDescricao("Blusa de maga,com estampas");
        assertTrue(produto.getDescricao().equals("Blusa de maga,com estampas"));
        produto.setTipo("Blusa");
        assertTrue(produto.getTipo().equals("Blusa"));
        produto.setPreco(new BigDecimal("5"));
        assertThat(produto.getPreco(), is(new BigDecimal("5")));
        produto.setCodigo("122523");
        assertTrue(produto.getCodigo().equals("122523"));
        produto.setQuantidade(3);
        assertTrue(produto.getQuantidade().equals(3));
    }

    // Testes HashCode, Equals e toString
    @Test
    public void deve_testar_equals_e_hashCode() {
        EqualsVerifier.forClass(Produto.class).suppress(NONFINAL_FIELDS).withIgnoredFields("descricao").withIgnoredFields("tipo").withIgnoredFields("preco").withIgnoredFields("quantidade").verify();
    }
    @Test
    public void deve_verificar_se_toString_contem_tipo() {
        assertTrue(produto1.toString().contains("tipo"));
    }

    @Test
    public void deve_verificar_se_toString_contem_preco() {
        assertTrue(produto1.toString().contains("preco"));
    }

    @Test
    public void deve_verificar_se_toString_contem_descricao() {
        assertTrue(produto1.toString().contains("descricao"));
    }

    @Test
    public void deve_verificar_se_toString_contem_codigo() {
        assertTrue(produto1.toString().contains("codigo"));
    }

    @Test
    public void deve_verificar_se_toString_contem_quantidade() {
        assertTrue(produto1.toString().contains("quantidade"));
    }
}
