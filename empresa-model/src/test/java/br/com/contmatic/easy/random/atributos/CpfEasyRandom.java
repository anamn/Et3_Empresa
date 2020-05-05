package br.com.contmatic.easy.random.atributos;

import org.jeasy.random.api.Randomizer;

import br.com.contmatic.randomicos.GeradorCpf;

public class CpfEasyRandom implements Randomizer<String> {

    @Override
    public String getRandomValue() {
        return new GeradorCpf().getCpfValido();
    }

}
