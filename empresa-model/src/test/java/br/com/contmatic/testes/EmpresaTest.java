package br.com.contmatic.testes;

import static br.com.contmatic.beanValidation.ValidationEmpresa.validador;
import static br.com.contmatic.easy.random.classes.EmpresaEasyRandomParametros.empresaValida;
import static br.com.contmatic.easy.random.classes.EnderecoEasyRandomParametros.parametrosEndereco;
import static br.com.contmatic.endereco.EnderecoType.CASA;
import static br.com.contmatic.telefone.Ddd.AM92;
import static br.com.contmatic.telefone.Ddd.SP11;
import static br.com.contmatic.telefone.TelefoneType.CELULAR;
import static br.com.contmatic.telefone.TelefoneType.FIXO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.RandomStringUtils;
import org.jeasy.random.EasyRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@FixMethodOrder(NAME_ASCENDING)
public class EmpresaTest {

    private Empresa empresa;

    @Before
    public void setUpBefore() {
        this.empresa = empresaValida();
    }

    @After
    public void setDownAfter() {
        this.empresa = null;
    }

    // Testa listas
    @Test
    public void nao_deve_adicionar_o_mesmo_produto() {
        Set<Produto> produtos = new HashSet<Produto>();
        produtos.add(new Produto("Notbook", "Notbook samsung versao 7", new BigDecimal("2500"), "12345678910", 3));
        produtos.add(new Produto("Notbook", "Notbook samsung versao 7", new BigDecimal("2500"), "12345678910", 3));
        produtos.add(new Produto("Notbook", "Notbook samsung versao 8", new BigDecimal("2500"), "123456654789", 3));
        empresa.setProdutos(produtos);
        assertTrue(empresa.getProdutos().size() == 2);
    }

    @Test
    public void nao_deve_adicionar_o_mesmo_telefone() {
        Set<Telefone> telefones = new HashSet<Telefone>();
        telefones.add(new Telefone(SP11, "986564582", CELULAR));
        telefones.add(new Telefone(AM92, "8656-4582", FIXO));
        telefones.add(new Telefone(AM92, "8656-4582", FIXO));
        empresa.setTelefones(telefones);
        assertTrue(empresa.getTelefones().size() == 2);
    }

    @Test
    public void nao_deve_adicionar_o_mesmo_endereco() {
        Set<Endereco> enderecos = new HashSet<Endereco>();
        enderecos.add(new Endereco("Rua Safira", "Vila Pedras", "45A", "03556878", CASA));
        enderecos.add(new Endereco("Rua Safira", "Vila Pedras", "45A", "03556878", CASA));
        enderecos.add(new Endereco("Rua Outono", "Vila Estacao", "65A", "03245875", CASA));
        empresa.setEnderecos(enderecos);
        assertTrue(empresa.getEnderecos().size() == 2);
    }

    @Test
    public void nao_deve_adicionar_o_mesmo_cliente() {
        Set<Cliente> clientes = new HashSet<>();
        Set<Telefone> telefones = new HashSet<Telefone>();
        Endereco endereco = new EasyRandom(parametrosEndereco()).nextObject(Endereco.class);
        clientes.add(new Cliente("Ana", "80974025054", telefones, "ana@hotmail.com", endereco));
        clientes.add(new Cliente("Ana", "80974025054", telefones, "ana@hotmail.com", endereco));
        clientes.add(new Cliente("João", "69460183034", telefones, "joazinho@hotmail.com", endereco));
        empresa.setClientes(clientes);
        assertTrue(empresa.getClientes().size() == 2);
    }

    @Test
    public void nao_deve_adicionar_o_mesmo_funcionario() {
        Set<Funcionario> funcionarios = new HashSet<>();
        Set<Telefone> telefones = new HashSet<Telefone>();
        Endereco endereco = new EasyRandom(parametrosEndereco()).nextObject(Endereco.class);
        funcionarios.add(new Funcionario("Ana", "80974025054", "53503636917", telefones, new BigDecimal("1500"), endereco));
        funcionarios.add(new Funcionario("Ana", "80974025054", "53503636917", telefones, new BigDecimal("1500"), endereco));
        funcionarios.add(new Funcionario("João", "80974025054", "94885325000", telefones, new BigDecimal("1500"), endereco));
        empresa.setFuncionarios(funcionarios);
        assertTrue(empresa.getFuncionarios().size() == 2);
    }

    // Testa atributos
    @Test
    public void nao_deve_retornar_erros_na_validacao_nos_atributos_de_identificacao() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(empresa)));
    }

    @Test
    public void nao_deve_retornar_erros_na_validacao_nos_atributos_de_contato() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(empresa)));
    }

    @Test
    public void nao_deve_retornar_erros_na_validacao_nos_atributos_de_localizacao() {
        Set<String> teste = new TreeSet<String>();
        assertThat(teste, is(validador(empresa)));
    }

    @Test
    public void deve_aceitar_espaco_no_nome() {
        Set<String> teste = new TreeSet<String>();
        empresa.setNome("Antone Flae");
        assertThat(teste, is(validador(empresa)));

    }

    @Test
    public void deve_aceitar_numero_no_nome() {
        Set<String> teste = new TreeSet<String>();
        empresa.setNome("Antone009");
        assertThat(teste, is(validador(empresa)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_nome_pelo_tamanho_maior() {
        empresa.setNome(RandomStringUtils.randomAlphabetic(51));
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_nome_pelo_tamanho_menor() {
        empresa.setNome(RandomStringUtils.randomAlphabetic(1));
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retornar_mensagem_de_erro_no_nome_null() {
        empresa.setNome(null);
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cnpj() {
        empresa.setCnpj("12345264578459");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_cnpj_nulo() {
        empresa.setCnpj(null);
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_site_sem_o_ponto_com_ponto_br() {
        empresa.setSite("onixbr");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_site_so_com_os_pontos() {
        empresa.setSite(".com.br");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_por_falta_de_arroba() {
        empresa.setEmail("anahotmail.com");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_por_falta_do_ponto() {
        empresa.setEmail("ana@hotmailcom");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_por_falta_do_com() {
        empresa.setEmail("ana@hotmail.");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_com_numero_depois_do_arroba() {
        empresa.setEmail("cliente@3873.com.br");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_com_letra_maiuscula() {
        empresa.setEmail("CLIENTE@HOTMAIL.COM");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_com_espaco() {
        empresa.setEmail("cliente @hotmail.com");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_sem_nada() {
        empresa.setEmail("cliente");
        validador(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_retonar_mensagem_de_erro_no_email_nulo() {
        empresa.setEmail(null);
        validador(empresa);
    }

    // Testa Construtor
    @Test
    public void deve_validaar_os_valores_passados() {
        empresa.setNome("Lg");
        assertEquals("Lg", empresa.getNome());
        empresa.setEmail("lg@gmail.com");
        assertEquals("lg@gmail.com", empresa.getEmail());
        empresa.setCnpj("02828446000134");
        assertEquals("02828446000134", empresa.getCnpj());
        empresa.setSite("www.lg.com.br");
        assertEquals("www.lg.com.br", empresa.getSite());

    }

    // Teste hashCode, Equals e toString
    @Test
    public void deve_testar_equals_e_hashCode() {
        EqualsVerifier.forClass(Empresa.class).withIgnoredFields("nome").withIgnoredFields("telefones").withIgnoredFields("enderecos").withIgnoredFields("email").withIgnoredFields("site")
                .withIgnoredFields("financas").withIgnoredFields("produtos").withIgnoredFields("funcionarios").withIgnoredFields("clientes").suppress(Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    public void deve_verificar_se_toString_contem_nome() {
        assertTrue(empresa.toString().contains("nome"));
    }

    @Test
    public void deve_verificar_se_toString_contem_cnpj() {
        assertTrue(empresa.toString().contains("cnpj"));
    }

    @Test
    public void deve_verificar_se_toString_contem_email() {
        assertTrue(empresa.toString().contains("email"));
    }

    @Test
    public void deve_verificar_se_toString_contem_telefones() {
        assertTrue(empresa.toString().contains("telefones"));
    }

    @Test
    public void deve_verificar_se_toString_contem_enderecos() {
        assertTrue(empresa.toString().contains("enderecos"));
    }

    @Test
    public void deve_verificar_se_toString_contem_clientes() {
        assertTrue(empresa.toString().contains("clientes"));
    }

    @Test
    public void deve_verificar_se_toString_contem_funcionarios() {
        assertTrue(empresa.toString().contains("funcionarios"));
    }

    @Test
    public void deve_verificar_se_toString_contem_produtos() {
        assertTrue(empresa.toString().contains("produtos"));
    }

    @Test
    public void deve_verificar_se_toString_contem_lucro() {
        assertTrue(empresa.toString().contains("financas"));
    }

    @Test
    public void deve_verificar_se_toString_contem_site() {
        assertTrue(empresa.toString().contains("site"));
    }

}
