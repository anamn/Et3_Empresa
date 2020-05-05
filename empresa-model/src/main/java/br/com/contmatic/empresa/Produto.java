package br.com.contmatic.empresa;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import br.com.contmatic.grupos.GrupoIdentificacao;
import br.com.contmatic.grupos.GrupoValores;


/**
 * The Class Produto.
 */
public final class Produto {

    /** The tipo. */
    @NotEmpty(message = "Tipo invalido", groups = GrupoIdentificacao.class)
    @Length(min = 2, max = 50, message = "Tipo invalido", groups = GrupoIdentificacao.class)
    private String tipo;

    /** The preco. */
    @NotNull(message = "Preço invalido", groups = GrupoValores.class)
    @DecimalMin(value = "1.00", message = "Preço invalido", groups = GrupoValores.class)
    private BigDecimal preco;

    /** The descricao. */
    @NotEmpty(message = "Descrição invalida", groups = GrupoIdentificacao.class)
    @Size(min = 10, max = 60, message = "Descrição invalida", groups = GrupoIdentificacao.class)
    private String descricao;

    /** The codigo. */
    @NotEmpty(message = "Codigo invalido", groups = GrupoIdentificacao.class)
    @Pattern(regexp = "[\\d]{5,30}", message = "Codigo invalido", groups = GrupoIdentificacao.class)
    private String codigo;

    /** The quantidade. */
    @NotNull(message = "Quantidade menor que zero", groups = GrupoValores.class)
    @Range(min = 1, max = 50, message = "Quantidade menor que zero", groups = GrupoValores.class)
    private Integer quantidade;
    
    /**
     * Instantiates a new produto.
     *
     * @param tipo the tipo
     * @param descricao the descricao
     * @param preco the preco
     * @param codigo the codigo
     * @param quantidade the quantidade
     */
    public Produto(String tipo, String descricao, BigDecimal preco, String codigo, Integer quantidade) {
        super();
        this.tipo = tipo;
        this.descricao = descricao;
        this.preco = preco;
        this.codigo = codigo;
        this.quantidade = quantidade;
    }

    /**
     * Instantiates a new produto.
     */
    public Produto() {
        super();
    }

    /**
     * Gets the descricao.
     *
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Sets the descricao.
     *
     * @param descricao the new descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Gets the tipo.
     *
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the tipo.
     *
     * @param tipo the new tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Gets the preco.
     *
     * @return the preco
     */
    public BigDecimal getPreco() {
        return preco;
    }

    /**
     * Sets the preco.
     *
     * @param preco the new preco
     */
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    /**
     * Gets the codigo.
     *
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the codigo.
     *
     * @param codigo the new codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;

    }

    /**
     * Gets the quantidade.
     *
     * @return the quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Sets the quantidade.
     *
     * @param quantidade the new quantidade
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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
        return new HashCodeBuilder().append(codigo).toHashCode();
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
        Produto other = (Produto) obj;
        return new EqualsBuilder().append(other.codigo, codigo).isEquals();

    }

}
