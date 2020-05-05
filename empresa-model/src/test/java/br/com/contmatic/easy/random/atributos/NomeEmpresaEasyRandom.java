package br.com.contmatic.easy.random.atributos;

import org.jeasy.random.api.Randomizer;

import com.github.javafaker.Faker;

public class NomeEmpresaEasyRandom implements Randomizer<String> {

    @Override
    public String getRandomValue() {
        return new Faker().name().firstName();
    }

}
