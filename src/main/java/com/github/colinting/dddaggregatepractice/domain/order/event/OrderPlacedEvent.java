package com.github.colinting.dddaggregatepractice.domain.order.event;

import com.github.colinting.dddaggregatepractice.domain.order.valueobject.OrderId;
import lombok.Getter;

@Getter
public class OrderPlacedEvent {

  // Order id
  private final OrderId orderId;

  public OrderPlacedEvent(OrderId orderId) {
    // Validate order id
    if (orderId == null) {
      throw new IllegalArgumentException("Order id cannot be null");
    }

    // Assign order id
    this.orderId = orderId;
  }
}