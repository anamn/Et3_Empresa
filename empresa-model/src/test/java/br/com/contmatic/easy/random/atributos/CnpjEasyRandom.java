package br.com.contmatic.easy.random.atributos;

import org.jeasy.random.api.Randomizer;

import br.com.contmatic.randomicos.GeradorDeCnpj;

public class CnpjEasyRandom implements Randomizer<String>{

    @Override
    public String getRandomValue() {
        return new GeradorDeCnpj().getCnpjValido();
    }

}
