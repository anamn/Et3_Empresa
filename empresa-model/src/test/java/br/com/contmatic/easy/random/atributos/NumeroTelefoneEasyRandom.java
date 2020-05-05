package br.com.contmatic.easy.random.atributos;

import org.jeasy.random.api.Randomizer;

import nl.flotsam.xeger.Xeger;

public class NumeroTelefoneEasyRandom implements Randomizer<String>{

    @Override
    public String getRandomValue() {
        return new Xeger("([9]?[0-9]{4}-?[0-9]{4})").generate();
    }

}
