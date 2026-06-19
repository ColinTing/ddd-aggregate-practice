package com.github.colinting.dddaggregatepractice.domain.order.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author 丁浩
 * @date 2022年09月03日 22:32
 */
@Getter
@EqualsAndHashCode
public class Quantity {

    // Quantity value
    private final BigDecimal value;

    // Zero quantity constant
    public static final Quantity ZERO = new Quantity(BigDecimal.ZERO);

    public Quantity(BigDecimal value) {
        // Validate value
        if (value == null || value.signum() < 0) {
            throw new IllegalArgumentException("Quantity value must be non-negative");
        }

        // Assign value
        this.value = value;
    }


    // Arithmetic operations

    public Quantity add(Quantity other) {
        // Validate other
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }

        // Add values and return new quantity
        return new Quantity(this.value.add(other.value));
    }

    public Quantity subtract(Quantity other) {
        // Validate other
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }

        // Subtract values and return new quantity
        return new Quantity(this.value.subtract(other.value));
    }

    // Comparison methods

    public boolean isZeroOrNegative() {
        return this.value.signum() <= 0;
    }

    public boolean isGreaterThan(Quantity other) {
        // Validate other
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }

        // Compare values and return result
        return this.value.compareTo(other.value) > 0;
    }

    public boolean isLessThan(Quantity other) {
        // Validate other
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }

        // Compare values and return result
        return this.value.compareTo(other.value) < 0;
    }

    public boolean isEqualTo(Quantity other) {
        // Validate other
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }

        // Compare values and return result
        return this.value.compareTo(other.value) == 0;
    }

}
