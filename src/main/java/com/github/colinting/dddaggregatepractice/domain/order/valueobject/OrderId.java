package com.github.colinting.dddaggregatepractice.domain.order.valueobject;


import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 *
 * @author 丁浩
 * @version 2026年06月15 20:40
 */
@Getter
@EqualsAndHashCode(of = "value")
public class OrderId {

    private final String value;

    public OrderId(String value) {
        // Validate value
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Order id value cannot be null or empty");
        }

        // Assign value
        this.value = value;
    }
}
