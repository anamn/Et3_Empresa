package br.com.contmatic.empresa;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.br.CPF;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.grupos.GrupoIdentificacao;
import br.com.contmatic.grupos.GrupoLocalizacao;
import br.com.contmatic.grupos.GrupoParaContato;
import br.com.contmatic.grupos.GrupoValores;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.validador.PIS;

/**
 * The Class Funcionario.
 */
public final class Funcionario {

    /** The salario. */
    @NotNull( message = "Salario invalido", groups = GrupoValores.class)
    @DecimalMin(value = "1.00", message = "Salario invalido", groups = GrupoValores.class)
    private BigDecimal salario;

    /** The pis. */
    @PIS(message = "Pis invalido", groups = GrupoIdentificacao.class)
    @NotEmpty(message = "Pis invalido", groups = GrupoIdentificacao.class)
    private String pis;

    /** The nome. */
    @NotEmpty(message = "Nome invalido", groups = GrupoIdentificacao.class)
    @Pattern(regexp = "[aA-zZáÁ-úÚ\\s]{2,50}", message = "Nome invalido", groups = GrupoIdentificacao.class)
    private String nome;
    
    /** The cpf. */
    @CPF(message = "CPF invalido", groups = GrupoIdentificacao.class)
    @NotNull(message = "CPF invalido", groups = GrupoIdentificacao.class)
    private String cpf;

    /** The telefones. */
    @NotNull(message = "Favor cadastrar ao menos um telefone", groups = GrupoParaContato.class)
    @Size(min = 1, max = 5, message = "É necessario ao menos um telefone no cadastro e no maximo cinco", groups = GrupoParaContato.class)
    private Set<Telefone> telefones;

    /** The endereco. */
    @NotNull(message = "Favor cadastrar ao menos um endereco", groups = GrupoLocalizacao.class)
    private Endereco endereco;

    /**
     * Instantiates a new funcionario.
     *
     * @param nome the nome
     * @param cpf the cpf
     * @param pis the pis
     * @param telefones the telefones
     * @param salario the salario
     * @param endereco the endereco
     */
    public Funcionario(String nome, String cpf, String pis, Set<Telefone> telefones, BigDecimal salario, Endereco endereco) {
        super();
        this.salario = salario;
        this.pis = pis;
        this.nome = nome;
        this.cpf = cpf;
        this.telefones = telefones;
        this.endereco = endereco;
    }

    /**
     * Instantiates a new funcionario.
     */
    public Funcionario() {
    }

    /**
     * Sets the nome.
     *
     * @param nome the new nome
     */
    public void setNome(String nome) {
        this.nome = nome;

    }

    /**
     * Gets the nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the cpf.
     *
     * @param cpf the new cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;

    }

    /**
     * Gets the cpf.
     *
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Gets the endereco.
     *
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * Sets the endereco.
     *
     * @param endereco the new endereco
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * Sets the telefones.
     *
     * @param tel the new telefones
     */
    public void setTelefones(Set<Telefone> tel) {
        this.telefones = tel;

    }

    /**
     * Gets the telefones.
     *
     * @return the telefones
     */
    public Set<Telefone> getTelefones() {
        return telefones;
    }

    /**
     * Gets the salario.
     *
     * @return the salario
     */
    public BigDecimal getSalario() {
        return salario;
    }

    /**
     * Sets the salario.
     *
     * @param salario the new salario
     */
    public void setSalario(BigDecimal salario) {
        this.salario = salario;

    }

    /**
     * Sets the pis.
     *
     * @param pis the new pis
     */
    public void setPis(String pis) {
        this.pis = pis;

    }

    /**
     * Gets the pis.
     *
     * @return the pis
     */
    public String getPis() {
        return pis;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JSON_STYLE);
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(pis).toHashCode();
    }

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Funcionario other = (Funcionario) obj;
        return new EqualsBuilder().append(other.pis, pis).isEquals();

    }

}
