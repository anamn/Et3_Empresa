package br.com.contmatic.easy.random.atributos;

import org.jeasy.random.api.Randomizer;

import nl.flotsam.xeger.Xeger;

public class TipoProdutoEasyRandom implements Randomizer<String> {

    @Override
    public String getRandomValue() {
        return new Xeger("[aA-zZáÁ-úÚ0-9]{3,50}").generate();
    }
}
