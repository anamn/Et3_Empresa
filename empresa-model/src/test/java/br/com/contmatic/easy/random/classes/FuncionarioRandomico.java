package br.com.contmatic.easy.random.classes;

import static br.com.contmatic.easy.random.classes.EnderecoEasyRandomParametros.parametrosEndereco;
import static br.com.contmatic.easy.random.classes.TelefoneEasyRandomParametros.parametrosTelefone;
import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.easy.random.atributos.CpfEasyRandom;
import br.com.contmatic.easy.random.atributos.NomePessoaEasyRandom;
import br.com.contmatic.easy.random.atributos.PisEasyRandom;
import br.com.contmatic.easy.random.atributos.ValoresEasyRandom;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;

public class FuncionarioRandomico {

    private static EasyRandomParameters parametrosFuncionario() {
        return new EasyRandomParameters().randomize(named("salario").and(ofType(BigDecimal.class)).and(inClass(Funcionario.class)), new ValoresEasyRandom())
                .randomize(named("nome").and(ofType(String.class)).and(inClass(Funcionario.class)), new NomePessoaEasyRandom())
                .randomize(named("cpf").and(ofType(String.class)).and(inClass(Funcionario.class)), new CpfEasyRandom())
                .randomize(named("pis").and(ofType(String.class)).and(inClass(Funcionario.class)), new PisEasyRandom());

    }

    public static Funcionario funcionarioValido() {
        Funcionario funcionario = new EasyRandom(parametrosFuncionario()).nextObject(Funcionario.class);
        Endereco endereco = new EasyRandom(parametrosEndereco()).nextObject(Endereco.class);
        Telefone telefone = new EasyRandom(parametrosTelefone()).nextObject(Telefone.class);
        Set<Telefone> telefones = new HashSet<Telefone>();
        telefones.add(telefone);
        funcionario.setTelefones(telefones);
        funcionario.setEndereco(endereco);
        return funcionario;
    }
}
