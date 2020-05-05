package br.com.contmatic.repository;

import static br.com.contmatic.easy.random.classes.EmpresaEasyRandomParametros.empresaValida;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
        for(int i = 0 ; i <= 10 ; i++) {
            empresa.setCnpj(new CnpjEasyRandom().getRandomValue());
            List<String> cnpjs = new ArrayList<>();
            if (!cnpjs.contains(empresa.getCnpj()))
                cnpjs.add(empresa.getCnpj());
            repository.adiciona(empresa);
        }
    }

    @Test
    public void a_deve_adicionar_empresa() {
        repository.adiciona(empresa);
        assertTrue(repository.sizeColletion() > tamanho);
    }

    @Test
    public void b_deve_pesquisar_empresa() {
        assertNotNull(repository.pesquisaEmpresaPeloCnpj("31030619000172"));
    }

    @Test
    public void d_deve_remover_empresa() {
        repository.remove("31030619000172");
        assertTrue(repository.sizeColletion() < tamanho);
    }

    @Test
    public void c_deve_atualizar_empresa() {
        this.empresa = empresaValida();
        empresa.setCnpj("31030619000172");
        repository.atualiza(empresa);
        assertTrue(repository.pesquisaEmpresaPeloCnpj("31030619000172").equals(Document.parse(empresa.toString()).append("_id", empresa.getCnpj())));
    }

    @Test
    public void deve_pesquisar__de_item_especifica_empresa() {
        List<String> componentes = new ArrayList<>();
        componentes.add("email");
        Document documento = new Document().append("_id", "36346956933789").append("email", "ana_nascimento@ig.com.br");
        documentos.add(documento);
        assertTrue(repository.pesquisaItens("36346956933789", componentes).equals(documentos));
    }

    @Test
    public void deve_retornar_todas_as_empresas_cadastradas() {
        System.out.println(repository.empresasCadastradas());
    }

    @Test
    public void deve_retornar_o_tamanho_da_empresa() {
        assertTrue(repository.sizeColletion() == 200000);
    }

    @Test
    public void deve_retornar_apenas_o_campo_pedido() {
        System.out.println(repository.pesquisaItens("nome", "Rick"));
    }
}
