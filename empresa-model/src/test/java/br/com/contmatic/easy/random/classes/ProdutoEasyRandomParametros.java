package br.com.contmatic.easy.random.classes;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

import java.math.BigDecimal;

import org.jeasy.random.EasyRandomParameters;

import br.com.contmatic.easy.random.atributos.CodigoEasyRandom;
import br.com.contmatic.easy.random.atributos.DescricaoEasyRandom;
import br.com.contmatic.easy.random.atributos.QuantidadeEasyRandom;
import br.com.contmatic.easy.random.atributos.TipoProdutoEasyRandom;
import br.com.contmatic.easy.random.atributos.ValoresEasyRandom;
import br.com.contmatic.empresa.Produto;

public class ProdutoEasyRandomParametros {

    public static EasyRandomParameters parametrosProduto() {
        return new EasyRandomParameters().randomize(named("tipo").and(ofType(String.class)).and(inClass(Produto.class)), new TipoProdutoEasyRandom())
                .randomize(named("descricao").and(ofType(String.class)).and(inClass(Produto.class)), new DescricaoEasyRandom())
                .randomize(named("preco").and(ofType(BigDecimal.class)).and(inClass(Produto.class)), new ValoresEasyRandom())
                .randomize(named("quantidade").and(ofType(Integer.class)).and(inClass(Produto.class)), new QuantidadeEasyRandom())
                .randomize(named("codigo").and(ofType(String.class)).and(inClass(Produto.class)), new CodigoEasyRandom());
    }
}
