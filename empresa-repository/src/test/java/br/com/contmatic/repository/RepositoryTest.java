package br.com.contmatic.repository;

import static br.com.contmatic.easy.random.classes.EmpresaEasyRandom.empresaValida;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.bson.Document;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mongodb.MongoClient;

import br.com.contmatic.easy.random.atributos.CnpjEasyRandom;
import br.com.contmatic.empresa.Empresa;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RepositoryTest {

    private static MongoClient local = new MongoClient();
    private Repository repository;
    private long tamanho;

    Empresa empresa;
    List<Document> documentos = new ArrayList<>();

    @Before
    public void setUpBefore() {
        this.repository = new Repository(local.getDatabase("Prova3"));
        this.empresa = empresaValida();
        empresa.setCnpj("31030619000172");
        this.tamanho = repository.sizeColletion();

    }

    @AfterClass
    public static void setDownAfterClass() {
        local.close();
    }

    @Test
    public void criacao_dos_registros() {
        this.empresa = empresaValida();
        for(int i = 0 ; i <= 5000 ; i++) {
            empresa.setCnpj(new CnpjEasyRandom().getRandomValue());
            List<String> cnpjs = new ArrayList<>();
            if (!cnpjs.contains(empresa.getCnpj()))
                cnpjs.add(empresa.getCnpj());
            repository.adiciona(empresa);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_empresa_nula() {
        this.empresa = null;
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_nome_invalido_tamanho_maior() {
        empresa.setNome(RandomStringUtils.randomAlphabetic(51));
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_nome_invalido_tamanho_menor() {
        empresa.setNome(RandomStringUtils.randomAlphabetic(1));
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_nome_null() {
        empresa.setNome(null);
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_cnpj_invalido() {
        empresa.setCnpj("12345264578459");
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_cnpj_invalido_nulo() {
        empresa.setCnpj(null);
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_site_sem_o_ponto_com_ponto_br() {
        empresa.setSite("onixbr");
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_site_so_com_os_pontos() {
        empresa.setSite(".com.br");
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_email_por_falta_de_arroba() {
        empresa.setEmail("anahotmail.com");
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_email_por_falta_do_ponto() {
        empresa.setEmail("ana@hotmailcom");
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_email_por_falta_do_com() {
        empresa.setEmail("ana@hotmail.");
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_email_com_numero_depois_do_arroba() {
        empresa.setEmail("cliente@3873.com.br");
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_email_com_letra_maiuscula() {
        empresa.setEmail("CLIENTE@HOTMAIL.COM");
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_email_com_espaco() {
        empresa.setEmail("cliente @hotmail.com");
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_email_sem_nada() {
        empresa.setEmail("cliente");
        repository.adiciona(empresa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_pelo_email_nulo() {
        empresa.setEmail(null);
        repository.adiciona(empresa);
    }

    @Test
    public void deve_adicionar_empresa() {
        repository.adiciona(empresa);
        assertTrue(repository.sizeColletion() > tamanho);
    }

    @Test
    public void deve_retornar_todas_as_empresas_cadastradas() {
        assertTrue(repository.empresasCadastradas().size() == 200466);
    }

    @Test
    public void deve_pesquisar_empresa() {
        assertTrue(repository.pesquisaEmpresaPeloCnpj("31030619000172").equals(empresa));
    }

    @Test
    public void z_deve_remover_empresa() {
        repository.remove("31030619000172");
        assertTrue(repository.sizeColletion() < tamanho);
    }

    @Test
    public void deve_atualizar_empresa() {
        Empresa novaEmpresa = empresaValida();
        novaEmpresa.setCnpj("31030619000172");
        repository.atualiza(empresa);
        assertTrue(repository.pesquisaEmpresaPeloCnpj("31030619000172").equals(novaEmpresa));
    }

    @Test
    public void deve_atualizar_apenas_um_item() {
        List<Filtro> filtro = new ArrayList<>();
        filtro.add(new Filtro("telefone", "Paula"));
        repository.atualiza("31030619000172", filtro);
        System.out.println(repository.pesquisaEmpresaPeloCnpj("31030619000172"));
    }

    @Test
    public void deve_atualizar_mais_itens() {
        List<Filtro> filtros = new ArrayList<>();
        filtros.add(new Filtro("nome", "Feijão"));
        filtros.add(new Filtro("email", "feijao@hotmail.com.br"));
        repository.atualiza("31030619000172", filtros);
        System.out.println(repository.pesquisaEmpresaPeloCnpj("31030619000172"));
    }

    @Test
    public void deve_pesquisar_de_itens() {
    }

    @Test
    public void deve_retornar_o_tamanho_da_empresa() {
        System.out.println(repository.sizeColletion());
        assertTrue(repository.sizeColletion() > 200000);
    }

    @Test
    public void deve_retornar_apenas_o_campo_pedido() {
        List<String> componentes = new ArrayList<>();
        componentes.add("nome");
        componentes.add("site");
        System.out.println(repository.pesquisaItens("64627905134612", componentes));
    }
}
