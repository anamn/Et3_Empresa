package br.com.contmatic.beanValidation;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.contmatic.telefone.Telefone;

public class ValidationTelefone {
    
    public static Set<String> validador(Telefone telefone, Class<?> grupo) {
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Telefone>> erros = validador.validate(telefone, grupo);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.size() <= 0, new IllegalAccessError(erros2.toString()));
        return erros2;

    }
}
