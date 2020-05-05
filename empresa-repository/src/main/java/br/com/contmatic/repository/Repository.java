package br.com.contmatic.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.assembler.EmpresaAssembly;
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
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Empresa>> erros = validador.validate(empresa);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        database.getCollection(COLLETION).insertOne(assembler.toDocument(empresa).append("_id", empresa.getCnpj()));
    }

    public void remove(String cnpj) {
        database.getCollection(COLLETION).findOneAndDelete(new Document().append("_id", cnpj));
    }

    public void remove(Empresa empresa) {
        database.getCollection(COLLETION).findOneAndDelete(new Document().append("_id", empresa.getCnpj()));
    }

    public Empresa pesquisaEmpresaPeloCnpj(String cnpj) {
        return assembler.toResource(database.getCollection(COLLETION).find(new Document().append("_id", cnpj)).first());
    }

    public void atualiza(Empresa novaEmpresa) {
        database.getCollection(COLLETION).findOneAndReplace(new Document().append("_id", novaEmpresa.getCnpj()), assembler.toDocument(novaEmpresa));
    }

    public Empresa pesquisa(String componente, String valor) {
        return assembler.toResource(database.getCollection(COLLETION).find().filter(new Document().append(componente, valor)).first());
    }

    public List<String> pesquisaItens(String cnpj, List<String> componentes) {
        List<String> pesquisa = new ArrayList<>();
        Empresa empresa = null;
        Filtro filtro= new Filtro();
        for(String string : componentes) {
            empresa = assembler.toResource(database.getCollection(COLLETION).find(new Document().append("_id", cnpj)).projection(new Document(string, 1)).first());
            filtro.verificaItens(empresa, pesquisa);
        }
        return pesquisa;
    }

}

