package br.com.contmatic.financeiro;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.YearMonth;

import br.com.contmatic.grupos.GrupoData;
import br.com.contmatic.grupos.GrupoValores;

/**
 * The Class Lucro.
 */
public final class Financas {

    /** The investimento. */
    @NotNull(message = "Investimento Nulo!", groups = GrupoValores.class)
    @DecimalMin(value = "1.00", message = "Investimento menor ou igual a zero!", groups = GrupoValores.class)
    private BigDecimal investimento;

    /** The renda. */
    @NotNull(message = "Renda Nulo!", groups = GrupoValores.class)
    @DecimalMin(value = "1.00", message = "Renda menor ou igual a zero!", groups = GrupoValores.class)
    private BigDecimal renda;

    /** The moeda. */
    private Moeda moeda;

    /** The mes. */
    @PastOrPresent(message = "A data deve ser no presente!", groups = GrupoData.class)
    private YearMonth mes;

    /**
     * Instantiates a new lucro.
     *
     * @param investimento the investimento
     * @param renda the renda
     * @param moeda the moeda
     * @param mes the mes
     */
    public Financas(BigDecimal investimento, BigDecimal renda, Moeda moeda, YearMonth mes) {
        super();
        this.moeda = moeda;
        this.renda = renda;
        this.investimento = investimento;
        this.mes = mes;

    }

    /**
     * Instantiates a new lucro.
     */
    public Financas() {
        super();
    }

    /**
     * Calcula lucro.
     *
     * @return the big decimal
     */
    public BigDecimal calculaLucro() {
        return getRenda().subtract(getInvestimento());
    }
    
    /**
     * Gets the investimento.
     *
     * @return the investimento
     */
    public BigDecimal getInvestimento() {
        return investimento;
    }

    /**
     * Sets the investimento.
     *
     * @param investimento the new investimento
     */
    public void setInvestimento(BigDecimal investimento) {
        this.investimento = investimento;
    }

    /**
     * Gets the renda.
     *
     * @return the renda
     */
    public BigDecimal getRenda() {
        return renda;
    }

    /**
     * Sets the renda.
     *
     * @param ganho the new renda
     */
    public void setRenda(BigDecimal ganho) {
        this.renda = ganho;

    }

    /**
     * Gets the moeda.
     *
     * @return the moeda
     */
    public Moeda getMoeda() {
        return moeda;
    }

    /**
     * Sets the moeda.
     *
     * @param moeda the new moeda
     */
    public void setMoeda(Moeda moeda) {
        this.moeda = moeda;

    }

    /**
     * Gets the mes.
     *
     * @return the mes
     */
    public YearMonth getMes() {
        return mes;
    }

    /**
     * Sets the mes.
     *
     * @param mes the new mes
     */
    public void setMes(YearMonth mes) {
        this.mes = mes;
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
        return new HashCodeBuilder().append(mes).toHashCode();
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
        Financas other = (Financas) obj;
        return new EqualsBuilder().append(other.mes, mes).isEquals();

    }

}
