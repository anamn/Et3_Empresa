package br.com.contmatic.easy.random.classes;

import static br.com.contmatic.easy.random.classes.ClienteEasyRandom.clienteValido;
import static br.com.contmatic.easy.random.classes.EnderecoEasyRandom.enderecoValido;
import static br.com.contmatic.easy.random.classes.FinancasEasyRandom.fincancaValida;
import static br.com.contmatic.easy.random.classes.FuncionarioEasyRandom.funcionarioValido;
import static br.com.contmatic.easy.random.classes.ProdutoEasyRandom.produtoValido;
import static br.com.contmatic.easy.random.classes.TelefoneEasyRandom.telefoneValido;
import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import java.util.HashSet;
import java.util.Set;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.easy.random.atributos.CnpjEasyRandom;
import br.com.contmatic.easy.random.atributos.EmailEasyRandom;
import br.com.contmatic.easy.random.atributos.NomeEmpresaEasyRandom;
import br.com.contmatic.easy.random.atributos.SiteEasyRandom;
import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;

public class EmpresaEasyRandom {

    private static EasyRandomParameters parametrosEmpresa() {
        return new EasyRandomParameters().randomize(named("nome").and(ofType(String.class)).and(inClass(Empresa.class)), new NomeEmpresaEasyRandom())
                .randomize(named("cnpj").and(ofType(String.class)).and(inClass(Empresa.class)), new CnpjEasyRandom())
                .randomize(named("email").and(ofType(String.class)).and(inClass(Empresa.class)), new EmailEasyRandom())
                .randomize(named("site").and(ofType(String.class)).and(inClass(Empresa.class)), new SiteEasyRandom());
    }

    public static Empresa empresaValida() {
        Empresa empresa = new EasyRandom(parametrosEmpresa()).nextObject(Empresa.class);
        Set<Endereco> enderecos = new HashSet<>();
        enderecos.add(enderecoValido());
        empresa.setEnderecos(enderecos);
        Set<Telefone> telefones = new HashSet<Telefone>();
        telefones.add(telefoneValido());
        empresa.setTelefones(telefones);
        Set<Funcionario> funcionarios = new HashSet<>();
        funcionarios.add(funcionarioValido());
        empresa.setFuncionarios(funcionarios);
        Set<Cliente> clientes = new HashSet<>();
        clientes.add(clienteValido());
        empresa.setClientes(clientes);
        Set<Produto> produtos = new HashSet<>();
        produtos.add(produtoValido());
        empresa.setProdutos(produtos);
        empresa.setFinancas(fincancaValida());
        return empresa;
    }
}
