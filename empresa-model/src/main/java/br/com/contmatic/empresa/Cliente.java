package br.com.contmatic.empresa;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.br.CPF;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.grupos.GrupoIdentificacao;
import br.com.contmatic.grupos.GrupoLocalizacao;
import br.com.contmatic.grupos.GrupoParaContato;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Cliente.
 */
public final class Cliente {

    /** The nome. */
    @NotEmpty(message = "Nome invalido", groups = GrupoIdentificacao.class)
    @Pattern(regexp = "[A-Z]{1}[aA-zZáÁ-úÚ\\s]{2,50}", message = "Nome invalido", groups = GrupoIdentificacao.class)
    private String nome;

    /** The cpf. */
    @NotEmpty(message = "Cpf invalido", groups = GrupoIdentificacao.class)
    @CPF(message = "CPF invalido", groups = GrupoIdentificacao.class)
    private String cpf;

    /** The telefones. */
    @Valid
    @NotNull(message = "Favor cadastrar telefone", groups = GrupoParaContato.class)
    @Size(min = 1, max = 5, message = "É necessario ao menos {min} telefone no cadastro e no maximo {max}", groups = GrupoParaContato.class)
    private Set<Telefone> telefones;

    /** The email. */
    @NotNull(message = "Email invalido", groups = GrupoParaContato.class)
    @Pattern(regexp = "[a-z]+[0-9.-_]*[a-z0-9.-_]+@[a-z]+[.]{1}[a-z]{2,5}[.a-z]{3}?", message = "Email invalido", groups = GrupoParaContato.class)
    private String email;

    /** The endereco. */
    @Valid
    @NotNull (message = "Favor cadastrar endereco", groups = GrupoLocalizacao.class)
    private Endereco endereco;

    /**
     * Instantiates a new cliente.
     *
     * @param nome the nome
     * @param cpf the cpf
     * @param telefones the telefones
     * @param email the email
     * @param endereco the endereco
     */
    public Cliente(String nome, String cpf, Set<Telefone> telefones, String email, Endereco endereco) {
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.telefones = telefones;
        this.endereco = endereco;
        this.email = email;
    }

    /**
     * Instantiates a new cliente.
     */
    public Cliente() {
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Sets the nome.
     *
     * @param nome the new nome
     */
    public void setNome(String nome) {
        this.nome = nome;
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
     * Sets the cpf.
     *
     * @param cpf the new cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
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
     * Sets the telefones.
     *
     * @param telefones the new telefones
     */
    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
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
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return reflectionToString(this, JSON_STYLE);
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cpf).toHashCode();
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
        Cliente other = (Cliente) obj;
        return new EqualsBuilder().append(other.cpf, cpf).isEquals();

    }

}
