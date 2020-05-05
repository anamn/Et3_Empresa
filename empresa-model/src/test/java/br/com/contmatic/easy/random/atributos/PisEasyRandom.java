package br.com.contmatic.easy.random.atributos;

import org.jeasy.random.api.Randomizer;

import br.com.contmatic.randomicos.GeradorDePis;

public class PisEasyRandom implements Randomizer<String>{

    @Override
    public String getRandomValue() {
        return new GeradorDePis().getPisValido();
    }

}
