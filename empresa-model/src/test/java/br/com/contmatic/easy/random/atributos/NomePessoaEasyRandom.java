package br.com.contmatic.easy.random.atributos;

import java.util.Arrays;
import java.util.Random;

import org.jeasy.random.api.Randomizer;

public class NomePessoaEasyRandom implements Randomizer<String> {

    private java.util.List<String> names = Arrays.asList("Ana Paula", "ANA NASCIMENTO", "João Pedro", "Júlia", "Patrícia", "Antônio");

    @Override
    public String getRandomValue() {
        return names.get(new Random().nextInt(1));
    }
}
