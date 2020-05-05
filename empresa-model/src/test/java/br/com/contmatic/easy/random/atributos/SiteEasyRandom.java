package br.com.contmatic.easy.random.atributos;

import org.jeasy.random.api.Randomizer;

import com.github.javafaker.Faker;

import nl.flotsam.xeger.Xeger;

public class SiteEasyRandom implements Randomizer<String> {

    @Override
    public String getRandomValue() {
        return new Xeger("http://" + new Faker().name().firstName() + "[.]{1}com[.]{1}br").generate();
    }

}
