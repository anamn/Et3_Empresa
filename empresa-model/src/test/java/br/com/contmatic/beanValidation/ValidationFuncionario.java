package br.com.contmatic.beanValidation;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.common.base.Preconditions;

import br.com.contmatic.empresa.Funcionario;

public abstract class ValidationFuncionario {

    public static Set<String> validador(Funcionario funcionario, Class<?> grupo) {
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Funcionario>> erros = validador.validate(funcionario, grupo);
        Set<String> erros2 = new TreeSet<>();
        erros.stream().forEach(erro -> erros2.add(erro.getMessage()));
        Preconditions.checkArgument(erros.isEmpty(), new IllegalAccessError(erros2.toString()));
        return erros2;

    }

}
