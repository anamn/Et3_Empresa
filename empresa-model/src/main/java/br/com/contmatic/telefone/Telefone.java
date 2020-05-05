package br.com.contmatic.telefone;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.contmatic.grupos.GrupoIdentificacao;

/**
 * The Class Telefone.
 */
public final class Telefone {

    /** The ddd. */
    @NotNull(message = "DDD nulo", groups = GrupoIdentificacao.class)
    private Ddd ddd;

    /** The numero. */
    @NotEmpty(message = "Numero invalido", groups = GrupoIdentificacao.class)
    @Pattern(regexp = "([9]?[0-9]{4}-?[0-9]{4})", message = "Numero invalido", groups = GrupoIdentificacao.class)
    private String numero;

    /** The tipo. */
    @NotNull(message = "Tipo nulo", groups = GrupoIdentificacao.class)
    private TelefoneType tipo;

    /**
     * Instantiates a new telefone.
     *
     * @param ddd the ddd
     * @param numero the numero
     * @param tipo the tipo
     */
    public Telefone(Ddd ddd, String numero, TelefoneType tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.ddd = ddd;
    }

    /**
     * Instantiates a new telefone.
     */
    public Telefone() {
    }
    
    /**
     * Gets the ddd.
     *
     * @return the ddd
     */
    public Ddd getDdd() {
        return ddd;
    }

    /**
     * Sets the ddd.
     *
     * @param ddd the new ddd
     */
    public void setDdd(Ddd ddd) {
        this.ddd = ddd;
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
     * Gets the tipo.
     *
     * @return the tipo
     */
    public TelefoneType getTipo() {
        return tipo;
    }

    /**
     * Sets the tipo.
     *
     * @param tipo the new tipo
     */
    public void setTipo(TelefoneType tipo) {
        this.tipo = tipo;
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
        return new HashCodeBuilder().append(numero).append(ddd).toHashCode();
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
        Telefone other = (Telefone) obj;
        return new EqualsBuilder().append(other.numero, numero).append(other.ddd, ddd).isEquals();

    }

}
