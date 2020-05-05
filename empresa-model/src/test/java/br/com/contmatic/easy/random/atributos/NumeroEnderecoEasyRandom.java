package br.com.contmatic.easy.random.atributos;

import org.jeasy.random.api.Randomizer;

import nl.flotsam.xeger.Xeger;

public class NumeroEnderecoEasyRandom implements Randomizer<String> {

    @Override
    public String getRandomValue() {
        return new Xeger("[1-9]{1}[0-9]{0,5}[aA-zZ]{0,2}").generate();
    }

}
