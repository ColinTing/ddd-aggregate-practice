package com.github.colinting.dddaggregatepractice.domain.order.factory;

import com.github.colinting.dddaggregatepractice.domain.order.entity.Order;
import com.github.colinting.dddaggregatepractice.domain.order.valueobject.OrderId;

import java.util.UUID;

public class OrderFactory {

  // Create a new order with a unique id
  public Order createNewOrder() {
    // Generate a unique id
    OrderId id = new OrderId(UUID.randomUUID().toString());

    // Create a new order
    Order order = new Order(id);

    // Return the order
    return order;
  }
}
