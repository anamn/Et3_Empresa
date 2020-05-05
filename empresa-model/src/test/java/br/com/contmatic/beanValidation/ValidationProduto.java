package br.com.contmatic.beanValidation;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.contmatic.empresa.Produto;

public class ValidationProduto {

    public static Set<String> validador(Produto produto, Class<?> grupo) {
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Produto>> erros = validador.validate(produto, grupo);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }

}
