package com.github.colinting.dddaggregatepractice.domain.order.support;

import com.github.colinting.dddaggregatepractice.domain.order.entity.Order;
import com.github.colinting.dddaggregatepractice.domain.order.valueobject.OrderId;

public interface OrderSupport {

  // Load an order by its id
  Order load(OrderId id);

  // Save an order
  void save(Order order);
}