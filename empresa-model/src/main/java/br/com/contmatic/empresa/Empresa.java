package br.com.contmatic.empresa;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.financeiro.Financas;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Empresa.
 */
public final class Empresa {

    /** The nome. */
    @NotEmpty(message = "Nome invalido")
    @Pattern(regexp = "[aA-zZáÁ-úÚ0-9\\s]{2,50}", message = "Nome invalido")
    private String nome;

    /** The cnpj. */
    @CNPJ(message = "CNPJ invalido")
    @NotEmpty(message = "CNPJ invalido")
    private String cnpj;

    /** The telefones. */
    @NotNull(message = "Favor cadastrar um telefone")
    @Size(min = 1, max = 5, message = "É necessario ao menos um telefone no cadastro e no maximo cinco")
    private Set<Telefone> telefones;

    /** The email. */
    @NotEmpty(message = "Email invalido")
    @Pattern(regexp = "[a-z]+[0-9.-_]*[a-z0-9.-_]+@[a-z]+[.]{1}[a-z]{2,5}[.a-z]{3}?", message = "Email invalido")
    private String email;

    /** The site. */
    @URL(message = "Site invalido")
    private String site;

    /** The enderecos. */
    @Valid
    @Size(min = 1, message = "É necessario ao menos um endereço no cadastro")
    private Set<Endereco> enderecos;

    /** The lucro. */
    private Financas financas;

    /** The clientes. */
    private Set<Cliente> clientes;

    /** The funcionarios. */
    private Set<Funcionario> funcionarios;

    /** The produtos. */
    private Set<Produto> produtos;

    /**
     * Instantiates a new empresa.
     *
     * @param nome the nome
     * @param cnpj the cnpj
     * @param telefones the telefones
     * @param email the email
     * @param endereco the endereco
     */
    public Empresa(String nome, String cnpj, Set<Telefone> telefones, String email, Set<Endereco> endereco) {
        super();
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefones = telefones;
        this.email = email;
        this.enderecos = endereco;

    }

    /**
     * Instantiates a new empresa.
     */
    public Empresa() {
        super();
    }

    /**
     * Gets the lucro.
     *
     * @return the lucro
     */
    public Financas getFinancas() {
        return financas;
    }

    /**
     * Sets the lucro.
     *
     * @param financas the new lucro
     */
    public void setFinancas(Financas financas) {
        this.financas = financas;
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
     * Gets the site.
     *
     * @return the site
     */
    public String getSite() {
        return site;
    }

    /**
     * Sets the site.
     *
     * @param site the new site
     */
    public void setSite(String site) {
        this.site = site;
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
     * Sets the cnpj.
     *
     * @param cnpj the new cnpj
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Gets the cnpj.
     *
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Gets the enderecos.
     *
     * @return the enderecos
     */
    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    /**
     * Sets the enderecos.
     *
     * @param endereco the new enderecos
     */
    public void setEnderecos(Set<Endereco> endereco) {
        this.enderecos = endereco;
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
     * Gets the clientes.
     *
     * @return the clientes
     */
    public Set<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Sets the clientes.
     *
     * @param clientes the new clientes
     */
    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * Gets the funcionarios.
     *
     * @return the funcionarios
     */
    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    /**
     * Sets the funcionarios.
     *
     * @param funcionarios the new funcionarios
     */
    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    /**
     * Gets the produtos.
     *
     * @return the produtos
     */
    public Set<Produto> getProdutos() {
        return produtos;
    }

    /**
     * Sets the produtos.
     *
     * @param produtos the new produtos
     */
    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
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
        return new HashCodeBuilder().append(cnpj).toHashCode();
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
        Empresa other = (Empresa) obj;
        return new EqualsBuilder().append(other.cnpj, cnpj).isEquals();
    }
}
