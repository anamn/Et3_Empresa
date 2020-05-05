package br.com.contmatic.validador;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorPis implements ConstraintValidator<PIS, String>{
  
    @Override
    public boolean isValid(String pis, ConstraintValidatorContext context) {
        int liTamanho = 0;
        StringBuilder lsAux = null;
        StringBuilder lsMultiplicador = new StringBuilder("3298765432");
        int liTotalizador = 0;
        int liResto = 0;
        int liMultiplicando = 0;
        int liMultiplicador = 0;
        boolean lbRetorno = true;
        int liDigito;
        
        lsAux = new StringBuilder().append(pis);
        liTamanho = lsAux.length();
        
        if (liTamanho != 11) {
            lbRetorno = false;
        }
        
        if (lbRetorno) {
            for (int i=0; i<10; i++) {
    
                liMultiplicando = Integer.parseInt(lsAux.substring(i, i+1));
                liMultiplicador = Integer.parseInt(lsMultiplicador.substring(i, i+1));
                liTotalizador += liMultiplicando * liMultiplicador;
            }
            
            liResto = 11 - liTotalizador % 11;
            liResto = liResto == 10 || liResto == 11 ? 0 : liResto;
            
            liDigito = Integer.parseInt("" + lsAux.charAt(10));
            lbRetorno = liResto == liDigito;
        }
        
        return lbRetorno;

    }
}
