package com.github.colinting.dddaggregatepractice.domain.order.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode
public class LineItem {

  // Line item attributes
  private final ProductId productId;
  private final String productName;
  private final Money productPrice;
  private final Quantity quantity;

  public LineItem(ProductId productId, String productName, Money productPrice,
                  Quantity quantity) {
    
     // Validate attributes
     if (productId == null) {
       throw new IllegalArgumentException("Product id cannot be null");
     }
     if (productName == null || productName.isEmpty()) {
       throw new IllegalArgumentException("Product name cannot be null or empty");
     }
     if (productPrice == null || productPrice.isZeroOrNegative()) {
       throw new IllegalArgumentException("Product price must be positive");
     }
     if (quantity == null || quantity.isZeroOrNegative()) {
       throw new IllegalArgumentException("Quantity must be positive");
     }

     // Assign attributes
     this.productId = productId;
     this.productName = productName;
     this.productPrice = productPrice;
     this.quantity = quantity;
  }

  // Derived attribute

  public Money getAmount() {
    return productPrice.multiply(quantity);
  }


}