package br.com.contmatic.easy.random.classes;

import static br.com.contmatic.easy.random.classes.ClienteRandomico.clienteValido;
import static br.com.contmatic.easy.random.classes.EnderecoEasyRandomParametros.parametrosEndereco;
import static br.com.contmatic.easy.random.classes.FuncionarioRandomico.funcionarioValido;
import static br.com.contmatic.easy.random.classes.FinancasEasyRandomParametros.parametrosLucro;
import static br.com.contmatic.easy.random.classes.ProdutoEasyRandomParametros.parametrosProduto;
import static br.com.contmatic.easy.random.classes.TelefoneEasyRandomParametros.parametrosTelefone;
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
import br.com.contmatic.financeiro.Financas;
import br.com.contmatic.telefone.Telefone;

public class EmpresaEasyRandomParametros {

    private static EasyRandomParameters parametrosEmpresa() {
        return new EasyRandomParameters().randomize(named("nome").and(ofType(String.class)).and(inClass(Empresa.class)), new NomeEmpresaEasyRandom())
                .randomize(named("cnpj").and(ofType(String.class)).and(inClass(Empresa.class)), new CnpjEasyRandom())
                .randomize(named("email").and(ofType(String.class)).and(inClass(Empresa.class)), new EmailEasyRandom())
                .randomize(named("site").and(ofType(String.class)).and(inClass(Empresa.class)), new SiteEasyRandom());
    }

    public static Empresa empresaValida() {
        Empresa empresa = new EasyRandom(parametrosEmpresa()).nextObject(Empresa.class);
        Endereco endereco = new EasyRandom(parametrosEndereco()).nextObject(Endereco.class);
        Set<Endereco> enderecos = new HashSet<>();
        enderecos.add(endereco);
        Telefone telefone = new EasyRandom(parametrosTelefone()).nextObject(Telefone.class);
        Set<Telefone> telefones = new HashSet<Telefone>();
        telefones.add(telefone);
        Set<Funcionario> funcionarios = new HashSet<>();
        funcionarios.add(funcionarioValido());
        Set<Cliente> clientes = new HashSet<>();
        clientes.add(clienteValido());
        Set<Produto> produtos = new HashSet<>();
        produtos.add(new EasyRandom(parametrosProduto()).nextObject(Produto.class));
        empresa.setEnderecos(enderecos);
        empresa.setTelefones(telefones);
        empresa.setFuncionarios(funcionarios);
        empresa.setClientes(clientes);
        empresa.setProdutos(produtos);
        empresa.setFinancas(new EasyRandom(parametrosLucro()).nextObject(Financas.class));
        return empresa;
    }

    public static void main(String[] args) {
        System.out.println(empresaValida());
    }
}
