package com.lucasgodoy.lojaki.domain.product.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

/**
 * Represents a monetary value in the system.
 *
 * This class is a Value Object in the Domain layer and encapsulates
 * both the amount and the currency of a monetary value.
 *
 * All business rules related to monetary calculations should use this class.
 */
public class Money {

    /**
     * The amount of money.
     * Must be non-negative.
     */
    private final BigDecimal amount;

    /**
     * The currency of the money.
     */
    private final Currency currency;

    /**
     * Constructor for Money.
     *
     * @param amount   Monetary value, must be non-negative
     * @param currency Currency of the value
     * @throws IllegalArgumentException if amount is negative or currency is null
     */
    public Money(BigDecimal amount, Currency currency) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency is required");
        }
        this.amount = amount.setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_EVEN);
        this.currency = currency;
    }

    // ===== Getters =====

    /**
     * Returns the monetary amount.
     *
     * @return BigDecimal amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Returns the currency of the money.
     *
     * @return Currency
     */
    public Currency getCurrency() {
        return currency;
    }

    // ===== Business methods =====

    /**
     * Adds another Money value to this one.
     *
     * @param other Money to add
     * @return New Money instance with the sum
     * @throws IllegalArgumentException if currencies do not match
     */
    public Money add(Money other) {
        checkCurrency(other);
        return new Money(this.amount.add(other.amount), currency);
    }

    /**
     * Subtracts another Money value from this one.
     *
     * @param other Money to subtract
     * @return New Money instance with the difference
     * @throws IllegalArgumentException if currencies do not match or result is negative
     */
    public Money subtract(Money other) {
        checkCurrency(other);
        BigDecimal result = this.amount.subtract(other.amount);
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Resulting amount cannot be negative");
        }
        return new Money(result, currency);
    }

    /**
     * Ensures that two Money objects have the same currency.
     *
     * @param other Money to compare currency
     * @throws IllegalArgumentException if currencies do not match
     */
    private void checkCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currencies must match");
        }
    }

    // ===== Overrides =====

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return amount.equals(money.amount) && currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return currency.getSymbol() + " " + amount;
    }
}
