package com.github.colinting.dddaggregatepractice.domain.order.entity;


import com.github.colinting.dddaggregatepractice.domain.order.event.OrderCanceledEvent;
import com.github.colinting.dddaggregatepractice.domain.order.event.OrderPlacedEvent;
import com.github.colinting.dddaggregatepractice.domain.order.event.OrderShippedEvent;
import com.github.colinting.dddaggregatepractice.domain.order.support.DomainEvents;
import com.github.colinting.dddaggregatepractice.domain.order.valueobject.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author 丁浩
 * @version 2026年06月15 20:29
 */

@Getter
public class Order {

    private final OrderId id;

    private OrderStatus status;

    private Money totalAmount;

    private final List<LineItem> lineItems;

    // Order behavior
    public Order(OrderId id) {
        // Validate id
        if (id == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        this.id = id;

        // Initialize state
        this.status = OrderStatus.NEW;
        this.totalAmount = Money.ZERO;
        this.lineItems = new ArrayList<>();
    }

    public void addLineItem(Product product, Quantity quantity) {
        // Validate product and quantity
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity == null || quantity.isZeroOrNegative()) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        // Check order status
        if (this.status != OrderStatus.NEW) {
            throw new IllegalStateException("Cannot add line item to non-new order");
        }

        // Create line item
        LineItem lineItem = new LineItem(product.getId(), product.getName(), product.getPrice(), quantity);

        // Add line item to list
        this.lineItems.add(lineItem);

        // Update total amount
        this.totalAmount = this.totalAmount.add(lineItem.getAmount());
    }

    public void place() {
        // Check order status
        if (this.status != OrderStatus.NEW) {
            throw new IllegalStateException("Cannot place non-new order");
        }

        // Check line items
        if (this.lineItems.isEmpty()) {
            throw new IllegalStateException("Cannot place empty order");
        }

        // Change order status
        this.status = OrderStatus.PLACED;

        // Publish domain event
        DomainEvents.publish(new OrderPlacedEvent(this.id));
    }

    public void cancel() {
        // Check order status
        if (this.status != OrderStatus.PLACED) {
            throw new IllegalStateException("Cannot cancel non-placed order");
        }

        // Change order status
        this.status = OrderStatus.CANCELED;

        // Publish domain event
        DomainEvents.publish(new OrderCanceledEvent(this.id));
    }

    public void ship() {
        // Check order status
        if (this.status != OrderStatus.PLACED) {
            throw new IllegalStateException("Cannot ship non-placed order");
        }

        // Change order status
        this.status = OrderStatus.SHIPPED;

        // Publish domain event
        DomainEvents.publish(new OrderShippedEvent(this.id));
    }
}
