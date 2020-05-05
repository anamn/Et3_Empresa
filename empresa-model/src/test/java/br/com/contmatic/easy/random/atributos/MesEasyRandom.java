package br.com.contmatic.easy.random.atributos;

import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;

import java.util.Arrays;
import java.util.Random;

import org.jeasy.random.api.Randomizer;
import org.joda.time.YearMonth;

public class MesEasyRandom implements Randomizer<YearMonth> {
    
    private java.util.List<YearMonth> meses= Arrays.asList(new YearMonth(2019, DECEMBER.getValue()), new YearMonth(2020, JANUARY.getValue()), new YearMonth(2020, FEBRUARY.getValue()));

    @Override
    public YearMonth getRandomValue() {
        return meses.get(new Random().nextInt(2));
    }

}
