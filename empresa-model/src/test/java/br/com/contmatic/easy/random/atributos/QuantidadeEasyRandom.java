package br.com.contmatic.easy.random.atributos;

import java.util.Random;

import org.jeasy.random.api.Randomizer;

public class QuantidadeEasyRandom implements Randomizer<Integer> {

    @Override
    public Integer getRandomValue() {
        return new Random().nextInt(10) + 1;
    }

}
