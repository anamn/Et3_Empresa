package br.com.contmatic.assembler;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClienteAssemblyTest.class, EmpresaAssemblyTest.class, EnderecoAssemblyTest.class, FinancasAssemblyTest.class, FuncionarioAssemblyTest.class, ProdutoAssemblyTest.class,
    TelefoneAssemblyTest.class })
public class SuiteAssemblyTest {

}

