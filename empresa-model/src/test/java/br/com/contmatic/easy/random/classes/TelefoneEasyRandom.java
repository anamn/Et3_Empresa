package br.com.contmatic.easy.random.classes;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.easy.random.atributos.NumeroTelefoneEasyRandom;
import br.com.contmatic.telefone.Telefone;

public class TelefoneEasyRandom {

    public static EasyRandomParameters parametrosTelefone() {
        return new EasyRandomParameters().randomize(named("numero").and(ofType(String.class)).and(inClass(Telefone.class)), new NumeroTelefoneEasyRandom());
    }
    
    public static Telefone telefoneValido() {
        return new EasyRandom(parametrosTelefone()).nextObject(Telefone.class);
    }
}