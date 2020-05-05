package br.com.contmatic.easy.random.atributos;

import org.jeasy.random.api.Randomizer;

import nl.flotsam.xeger.Xeger;

public class CepEasyRandom implements Randomizer<String>{

    @Override
    public String getRandomValue() {
        return new Xeger("([0-9]{5}-?[0-9]{3})").generate();
    }

}
