package br.com.contmatic.easy.random.atributos;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import java.math.BigDecimal;

import org.jeasy.random.api.Randomizer;

public class ValoresEasyRandom implements Randomizer<BigDecimal> {

    @Override
    public BigDecimal getRandomValue() {
        return new BigDecimal(randomNumeric(2, 10));
    }
}
