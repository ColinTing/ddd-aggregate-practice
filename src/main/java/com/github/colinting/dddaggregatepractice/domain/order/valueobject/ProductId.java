package com.github.colinting.dddaggregatepractice.domain.order.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ProductId {

  // Product id value
  private final String value;

  public ProductId(String value) {
    // Validate value
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException("Product id value cannot be null or empty");
    }

    // Assign value
    this.value = value;
  }



}