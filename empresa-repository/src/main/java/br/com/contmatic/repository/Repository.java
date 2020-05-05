package br.com.contmatic.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.bson.Document;

import com.mongodb.client.MongoDatabase;

import br.com.contmatic.empresa.Empresa;

public class Repository {

    private MongoDatabase database;
    private static final String COLLETION = "Empresa";

    public Repository(MongoDatabase database) {
        super();
        this.database = database;
    }

    public long sizeColletion() {
        return database.getCollection(COLLETION).countDocuments();
    }

    public List<Document> empresasCadastradas() {
        List<Document> empresas = new ArrayList<>();
        for(int i = 0 ; database.getCollection(COLLETION).countDocuments() > i ; i++) {
            empresas.add(database.getCollection(COLLETION).find().first());
        }
        return empresas;
    }
    
    public void adiciona(Empresa empresa) {
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Empresa>> erros = validador.validate(empresa);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
            database.getCollection(COLLETION).insertOne(Document.parse(empresa.toString()).append("_id", empresa.getCnpj()));
        }

    public void remove(String cnpj) {
        database.getCollection(COLLETION).findOneAndDelete(new Document().append("_id", cnpj));
    }

    public void remove(Empresa empresa) {
        database.getCollection(COLLETION).findOneAndDelete(new Document().append("_id", empresa.getCnpj()));
    }

    public Document pesquisaEmpresaPeloCnpj(String cnpj) {
        return database.getCollection(COLLETION).find(new Document().append("_id", cnpj)).first();
    }

    public void atualiza(Empresa novaEmpresa) {
        database.getCollection(COLLETION).findOneAndReplace(new Document().append("_id", novaEmpresa.getCnpj()), Document.parse(novaEmpresa.toString()));
    }

    public Document pesquisa(String componente, String valor) {
        return database.getCollection(COLLETION).find().filter(new Document().append(componente, valor)).first();
    }

    public Document pesquisaItens(String componente, String valor) {
        return database.getCollection(COLLETION).find().filter(new Document().append(componente, valor)).projection(new Document(componente, 1)).first();
    }

    public List<Document> pesquisaItens(String cnpj, List<String> componentes) {
        List<Document> pesquisa = new ArrayList<>();
        for(String string : componentes) {
            pesquisa.add(database.getCollection(COLLETION).find(new Document().append("_id", cnpj)).projection(new Document(string, 1)).first());
        }
        return pesquisa;
    }

}
