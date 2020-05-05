package br.com.contmatic.easy.random.atributos;

import java.util.Arrays;
import java.util.Random;

import org.jeasy.random.api.Randomizer;

public class EmailEasyRandom implements Randomizer<String> {

    private java.util.List<String> email = Arrays.asList("ana_nascimento@ig.com.br", "ana-nascim3nto@gmail.com", "ana.nascimento@empresa.com.br", "ananascimento@hotmail.com", "empresa09@empresa.ig");

    @Override
    public String getRandomValue() {
        return email.get(new Random().nextInt(1));
    }

}
