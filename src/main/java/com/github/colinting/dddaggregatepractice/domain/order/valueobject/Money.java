package com.github.colinting.dddaggregatepractice.domain.order.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;


/**
 * @author 丁浩
 * @date 2022年08月09日 10:00
 */
@Getter
@EqualsAndHashCode
public class Money {

    // Money value
    private final BigDecimal value;

    // Zero money constant
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money(BigDecimal value) {
        // Validate value
        if (value == null || value.signum() < 0) {
            throw new IllegalArgumentException("Money value must be non-negative");
        }

        // Assign value
        this.value = value;
    }


    // Arithmetic operations

    public Money add(Money other) {
        // Validate other
        if (other == null) {
            throw new IllegalArgumentException("Other money cannot be null");
        }

        // Add values and return new money
        return new Money(this.value.add(other.value));
    }

    public Money subtract(Money other) {
        // Validate other
        if (other == null) {
            throw new IllegalArgumentException("Other money cannot be null");
        }

        // Subtract values and return new money
        return new Money(this.value.subtract(other.value));
    }

    public Money multiply(Quantity quantity) {
        // Validate quantity
        if (quantity == null || quantity.isZeroOrNegative()) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        // Multiply values and return new money
        return new Money(this.value.multiply(quantity.getValue()));
    }

    // Comparison methods

    public boolean isZeroOrNegative() {
        return this.value.signum() <= 0;
    }

    public boolean isGreaterThan(Money other) {
        // Validate other
        if (other == null) {
            throw new IllegalArgumentException("Other money cannot be null");
        }

        // Compare values and return result
        return this.value.compareTo(other.value) > 0;
    }

    public boolean isLessThan(Money other) {
        // Validate other
        if (other == null) {
            throw new IllegalArgumentException("Other money cannot be null");
        }

        // Compare values and return result
        return this.value.compareTo(other.value) < 0;
    }

    public boolean isEqualTo(Money other) {
        // Validate other
        if (other == null) {
            throw new IllegalArgumentException("Other money cannot be null");
        }

        // Compare values and return result
        return this.value.compareTo(other.value) == 0;
    }

}
