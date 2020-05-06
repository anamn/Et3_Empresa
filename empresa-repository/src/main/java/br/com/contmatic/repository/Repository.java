package br.com.contmatic.repository;

import static br.com.contmatic.beanValidation.ValidationEmpresa.validador;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.assembler.EmpresaAssembly;
import br.com.contmatic.beanValidation.ValidationEmpresa;
import br.com.contmatic.empresa.Empresa;

public class Repository {

    private MongoDatabase database;
    private EmpresaAssembly assembler = new EmpresaAssembly();
    private static final String COLLETION = "Empresa";

    public Repository(MongoDatabase database) {
        super();
        this.database = database;
    }

    public long sizeColletion() {
        return database.getCollection(COLLETION).countDocuments();
    }

    public List<Empresa> empresasCadastradas() {
        FindIterable<Document> documentos = database.getCollection(COLLETION).find();
        List<Empresa> empresas = new ArrayList<>();
        for(Document documento : documentos) {
            empresas.add(assembler.toResource(documento));
        }
        return empresas;

    }

    public void adiciona(Empresa empresa) {
        ValidationEmpresa.validador(empresa);
        database.getCollection(COLLETION).insertOne(assembler.toDocument(empresa).append("_id", empresa.getCnpj()));

    }

    public void remove(String cnpj) {
        database.getCollection(COLLETION).findOneAndDelete(new Document().append("_id", cnpj));
    }

    public void remove(Empresa empresa) {
        database.getCollection(COLLETION).findOneAndDelete(new Document().append("_id", empresa.getCnpj()));
    }

    public void atualiza(Empresa novaEmpresa) {
        validador(novaEmpresa);
        database.getCollection(COLLETION).findOneAndReplace(new Document().append("_id", novaEmpresa.getCnpj()), assembler.toDocument(novaEmpresa));
    }

    public void atualiza(String cnpj, List<Filtro> filtros) {
        Document document = Document.parse(this.pesquisaEmpresaPeloCnpj(cnpj).toString());
        for(Filtro filtro : filtros) {
            if (!filtro.getComponente().equals("cpf") || !filtro.getComponente().equals("_id")) {
                if(filtro.getComponente().equals("telefone")) {
                    database.getCollection(COLLETION).findOneAndReplace(new Document().append("_id", cnpj), document.append(filtro.getComponente(), filtro.getValores()));  
                }
                database.getCollection(COLLETION).findOneAndReplace(new Document().append("_id", cnpj), document.append(filtro.getComponente(), filtro.getValor()));
            }
            try{
                validador(this.pesquisaEmpresaPeloCnpj(cnpj));
            }catch (IllegalArgumentException e) {
                this.remove(cnpj);
            }
        }
    }

    public Empresa pesquisaEmpresaPeloCnpj(String cnpj) {
        return assembler.toResource(database.getCollection(COLLETION).find(new Document().append("_id", cnpj)).first());
    }
    
    public List<String> pesquisaItens(String cnpj, List<String> componentes) {
        List<String> pesquisa = new ArrayList<>();
        Empresa empresa = null;
        Filtro filtro = new Filtro();
        for(String string : componentes) {
            empresa = assembler.toResource(database.getCollection(COLLETION).find(new Document().append("_id", cnpj)).projection(new Document(string, 1)).first());
            filtro.verificaItens(empresa, pesquisa);
        }
        return pesquisa;
    }

}
