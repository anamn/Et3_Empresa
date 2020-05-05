package br.com.contmatic.endereco;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.contmatic.grupos.GrupoIdentificacao;

/**
 * The Class Endereco.
 */
public final class Endereco {

    /** The endereco. */
    @NotEmpty(message = "Logradouro invalido!", groups = GrupoIdentificacao.class)
    @Pattern(regexp = "[aA-zZáÁ-úÚ\\s]{2,40}", message = "Logradouro invalido!", groups = GrupoIdentificacao.class)
    private String logradouro;

    @NotEmpty(message = "Bairro invalido!", groups = GrupoIdentificacao.class)
    @Pattern(regexp = "[aA-zZáÁ-úÚ\\s]{3,40}", message = "Bairro invalido!", groups = GrupoIdentificacao.class)
    private String bairro;

    /** The numero. */
    @NotEmpty(message = "Numero invalido!", groups = GrupoIdentificacao.class)
    @Pattern(regexp = "[1-9]{1}+[0-9]{0,5}[aA-zZ]{0,2}", message = "Numero invalido!", groups = GrupoIdentificacao.class)
    private String numero;

    /** The cep. */
    @NotEmpty(message = "CEP invalido!", groups = GrupoIdentificacao.class)
    @Pattern(regexp = "([\\d]{5}-?[\\d]{3})", message = "CEP invalido!", groups = GrupoIdentificacao.class)
    private String cep;

    /** The endereco type. */
    @NotNull(message = "É necessario informar o tipo de moradia", groups = GrupoIdentificacao.class)
    private EnderecoType tipo;

    /**
     * Instantiates a new endereco.
     *
     * @param logradouro the endereco
     * @param numero the numero
     * @param cep the cep
     * @param enderecoType the endereco type
     */
    public Endereco(String logradouro, String bairro, String numero, String cep, EnderecoType enderecoType) {
        super();
        this.logradouro = logradouro;
        this.cep = cep;
        this.bairro = bairro;
        this.numero = numero;
        this.tipo = enderecoType;
    }

    /**
     * Instantiates a new endereco.
     */
    public Endereco() {
        super();
    }

    /**
     * Gets the endereco.
     *
     * @return the endereco
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * Sets the endereco.
     *
     * @param logradouro the new endereco
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Gets the numero.
     *
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the numero.
     *
     * @param numero the new numero
     */
    public void setNumero(String numero) {
        this.numero = numero;

    }

    /**
     * Gets the cep.
     *
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * Sets the cep.
     *
     * @param cep the new cep
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * Gets the endereco type.
     *
     * @return the endereco type
     */
    public EnderecoType getEnderecoType() {
        return tipo;
    }

    /**
     * Sets the endereco type.
     *
     * @param enderecoType the new endereco type
     */
    public void setEnderecoType(EnderecoType enderecoType) {
        this.tipo = enderecoType;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cep).append(logradouro).append(numero).append(bairro).toHashCode();
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
        Endereco other = (Endereco) obj;
        return new EqualsBuilder().append(other.cep, cep).append(other.logradouro, logradouro).append(other.numero, numero).append(other.bairro, bairro).isEquals();

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

}
