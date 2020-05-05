package br.com.contmatic.testes;

import static br.com.contmatic.beanValidation.ValidationCliente.validador;
import static br.com.contmatic.easy.random.classes.ClienteRandomico.clienteValido;
import static br.com.contmatic.telefone.Ddd.AM92;
import static br.com.contmatic.telefone.Ddd.SP11;
import static br.com.contmatic.telefone.TelefoneType.CELULAR;
import static br.com.contmatic.telefone.TelefoneType.FIXO;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import static nl.jqno.equalsverifier.Warning.NONFINAL_FIELDS;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.grupos.GrupoIdentificacao;
import br.com.contmatic.grupos.GrupoLocalizacao;
import br.com.contmatic.grupos.GrupoParaContato;
import br.com.contmatic.telefone.Telefone;

@FixMethodOrder(NAME_ASCENDING)
public class ClienteTest {

    private Cliente cliente;


    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.six2six.fixturefactory.loader");
    }

    @Before
    public void incializacao() {
        this.cliente = clienteValido();

    }

    @After
    public void finaliza() {
        this.cliente = null;
    }

    @Test
    public void nao_deve_adicionar_o_mesmo_telefone() {
        Set<Telefone> telefones = new HashSet<Telefone>();
        telefones.add(new Telefone(SP11, "986564582", CELULAR));
        telefones.add(new Telefone(AM92, "8656-4582", FIXO));
        telefones.add(new Telefone(AM92, "8656-4582", FIXO));
        cliente.setTelefones(telefones);
        assertTrue(cliente.getTelefones().size() == 2);
    }

    // Testa atributos
    @Test
    public void nao_deve_retornar_erros_de_validacao_no_atributos_de_identificacao() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(cliente, GrupoIdentificacao.class)));
    }
    
    @Test
    public void nao_deve_retornar_erros_de_validacao_no_atributos_de_contato() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(cliente, GrupoParaContato.class)));
    }
    
    @Test
    public void nao_deve_retornar_erros_de_validacao_no_atributos_de_localizacao() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(cliente, GrupoLocalizacao.class)));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_por_tamanho_de_nome_maior() {
        cliente.setNome(randomAlphabetic(52));
        validador(cliente, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_por_tamanho_de_nome_menor() {
        cliente.setNome(randomAlphabetic(1));
        validador(cliente, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_nulo() {
        cliente.setNome(null);
        validador(cliente, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_com_numero() {
        cliente.setNome("Ana098");
        validador(cliente, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_nome_com_caracteres_especiais() {
        cliente.setNome("Ana_*");
        validador(cliente, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf() {
        cliente.setCpf(randomNumeric(11));
        validador(cliente, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_pelo_tamanho_maior() {
        cliente.setCpf(randomNumeric(12));
        validador(cliente, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_caracter_especial() {
        cliente.setCpf("112542654_*");
        validador(cliente, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_letra() {
        cliente.setCpf("112542654aa");
        validador(cliente, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_pelo_tamanho_menor() {
        cliente.setCpf(randomNumeric(10));
        validador(cliente, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cpf_nulo() {
        cliente.setCpf(null);
        validador(cliente, GrupoIdentificacao.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_por_falta_de_arroba() {
        cliente.setEmail("anahotmail.com");
        validador(cliente, GrupoParaContato.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_por_falta_do_ponto() {
        cliente.setEmail("ana@hotmailcom");
        validador(cliente, GrupoParaContato.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_por_falta_do_com() {
        cliente.setEmail("ana@hotmail.");
        validador(cliente, GrupoParaContato.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_com_numero_depois_do_arroba() {
        cliente.setEmail("cliente@3873.com.br");
        validador(cliente, GrupoParaContato.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_com_letra_maiuscula() {
        cliente.setEmail("CLIENTE@HOTMAIL.COM");
        validador(cliente, GrupoParaContato.class);;
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_com_espaco() {
        cliente.setEmail("cliente @hotmail.com");
        validador(cliente, GrupoParaContato.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_sem_nada() {
        cliente.setEmail("cliente");
        validador(cliente, GrupoParaContato.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_null() {
        cliente.setEmail(null);
        validador(cliente, GrupoParaContato.class);
    }

    // Testa Construtor
    @Test
    public void deve_retornar_valores_passados() {
        cliente.setNome("Maria");
        assertTrue(cliente.getNome().equals("Maria"));
        cliente.setCpf("32179960387");
        assertTrue(cliente.getCpf().equals("32179960387"));
        cliente.setEmail("maria@hotmail.com");
        assertTrue(cliente.getEmail().equals("maria@hotmail.com"));
    }

    // Teste hashCode, Equals e toString
    @Test
    public void deve_testar_equals_e_hashCode() {
        forClass(Cliente.class).suppress(NONFINAL_FIELDS).withIgnoredFields("nome").withIgnoredFields("endereco").withIgnoredFields("telefones").withIgnoredFields("email").verify();
    }

    @Test
    public void deve_verificar_se_toString_contem_nome() {
        assertTrue(cliente.toString().contains("nome"));
    }

    @Test
    public void deve_verificar_se_toString_contem_cpf() {
        assertTrue(cliente.toString().contains("cpf"));
    }

    @Test
    public void deve_verificar_se_toString_contem_telefones() {
        assertTrue(cliente.toString().contains("telefones"));
    }

    @Test
    public void deve_verificar_se_toString_contem_email() {
        assertTrue(cliente.toString().contains("email"));
    }

    @Test
    public void deve_verificar_se_toString_contem_endereco() {
        assertTrue(cliente.toString().contains("endereco"));
    }
}
