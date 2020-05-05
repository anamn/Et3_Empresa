package br.com.contmatic.easy.random.classes;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.easy.random.atributos.BairroEasyRandom;
import br.com.contmatic.easy.random.atributos.CepEasyRandom;
import br.com.contmatic.easy.random.atributos.LogradouroEasyRandom;
import br.com.contmatic.easy.random.atributos.NumeroEnderecoEasyRandom;
import br.com.contmatic.endereco.Endereco;

public class EnderecoEasyRandom {

    public static EasyRandomParameters parametrosEndereco() {
        return new EasyRandomParameters().randomize(named("logradouro").and(ofType(String.class)).and(inClass(Endereco.class)), new LogradouroEasyRandom())
                .randomize(named("bairro").and(ofType(String.class)).and(inClass(Endereco.class)), new BairroEasyRandom())
                .randomize(named("numero").and(ofType(String.class)).and(inClass(Endereco.class)), new NumeroEnderecoEasyRandom())
                .randomize(named("cep").and(ofType(String.class)).and(inClass(Endereco.class)), new CepEasyRandom());
    }
    
    public static Endereco enderecoValido() {
        return new EasyRandom(parametrosEndereco()).nextObject(Endereco.class);
    }
}
