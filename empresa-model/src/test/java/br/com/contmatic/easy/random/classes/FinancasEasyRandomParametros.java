package br.com.contmatic.easy.random.classes;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import java.math.BigDecimal;

import org.jeasy.random.EasyRandomParameters;
import org.joda.time.YearMonth;

import br.com.contmatic.easy.random.atributos.MesEasyRandom;
import br.com.contmatic.easy.random.atributos.ValoresEasyRandom;
import br.com.contmatic.financeiro.Financas;

public class FinancasEasyRandomParametros {

    public static EasyRandomParameters parametrosLucro() {
        return new EasyRandomParameters().randomize(named("investimento").and(ofType(BigDecimal.class)).and(inClass(Financas.class)), new ValoresEasyRandom())
                .randomize(named("renda").and(ofType(BigDecimal.class).and(inClass(Financas.class))), new ValoresEasyRandom())
                .randomize(named("mes").and(ofType(YearMonth.class)).and(inClass(Financas.class)), new MesEasyRandom());
    }
}
