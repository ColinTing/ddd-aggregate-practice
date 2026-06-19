package com.github.colinting.dddaggregatepractice.domain.order.entity;

import com.github.colinting.dddaggregatepractice.domain.order.event.OrderCanceledEvent;
import com.github.colinting.dddaggregatepractice.domain.order.event.OrderPlacedEvent;
import com.github.colinting.dddaggregatepractice.domain.order.event.OrderShippedEvent;
import com.github.colinting.dddaggregatepractice.domain.order.support.DomainEvents;
import com.github.colinting.dddaggregatepractice.domain.order.valueobject.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// OrderTest.java
class OrderTest {

  // Test data
  private OrderId orderId;
  private ProductId productId;
  private String productName;
  private Money productPrice;
  private Quantity quantity;

  // Test objects
  private Order order;
  private Product product;
  private LineItem lineItem;
  private List<Object> events;

  // Test setup
  @BeforeEach
  void setUp() {
    // Create test data
    orderId = new OrderId("O-123");
    productId = new ProductId("P-456");
    productName = "Test Product";
    productPrice = new Money(new BigDecimal("10.00"));
    quantity = new Quantity(new BigDecimal("2"));

    // Create test objects
    order = new Order(orderId);
    product = new Product(productId, productName, productPrice);
    lineItem = new LineItem(productId, productName, productPrice, quantity);

    events = new ArrayList<>();
    DomainEvents.clearHandlers();
    DomainEvents.register(events::add);
  }

  @AfterEach
  void tearDown() {
    DomainEvents.clearHandlers();
  }

  // Test adding a line item to a new order
  @Test
  void testAddLineItemToNewOrder() {
    // Add line item to order
    order.addLineItem(product, quantity);

    // Verify order state
    assertEquals(OrderStatus.NEW, order.getStatus());
    assertEquals(new Money(new BigDecimal("20.00")), order.getTotalAmount());
    assertEquals(1, order.getLineItems().size());
    assertTrue(order.getLineItems().contains(lineItem));
  }

  // Test placing a non-empty order
  @Test
  void testPlaceNonEmptyOrder() {
    // Add line item to order
    order.addLineItem(product, quantity);

    // Place order
    order.place();

    // Verify order state
    assertEquals(OrderStatus.PLACED, order.getStatus());
    assertEquals(new Money(new BigDecimal("20.00")), order.getTotalAmount());
    assertEquals(1, order.getLineItems().size());
    assertTrue(order.getLineItems().contains(lineItem));

    // Verify domain event
    assertEquals(1, events.size());
    OrderPlacedEvent event = assertInstanceOf(OrderPlacedEvent.class, events.getFirst());
    assertEquals(orderId, event.getOrderId());
  }

  // Test canceling a placed order
  @Test
  void testCancelPlacedOrder() {
     // Add line item to order
     order.addLineItem(product, quantity);

     // Place order
     order.place();

     // Cancel order
     order.cancel();

     // Verify order state
     assertEquals(OrderStatus.CANCELED, order.getStatus());
     assertEquals(new Money(new BigDecimal("20.00")), order.getTotalAmount());
     assertEquals(1, order.getLineItems().size());
     assertTrue(order.getLineItems().contains(lineItem));

     // Verify domain event
     assertEquals(2, events.size());
     OrderCanceledEvent event = assertInstanceOf(OrderCanceledEvent.class, events.get(1));
     assertEquals(orderId, event.getOrderId());
   }

   // Test shipping a placed order
   @Test
   void testShipPlacedOrder() {
      // Add line item to order
      order.addLineItem(product, quantity);

      // Place order
      order.place();

      // Ship order
      order.ship();

      // Verify order state
      assertEquals(OrderStatus.SHIPPED, order.getStatus());
      assertEquals(new Money(new BigDecimal("20.00")), order.getTotalAmount());
      assertEquals(1, order.getLineItems().size());
      assertTrue(order.getLineItems().contains(lineItem));

      // Verify domain event
      assertEquals(2, events.size());
      OrderShippedEvent event = assertInstanceOf(OrderShippedEvent.class, events.get(1));
      assertEquals(orderId, event.getOrderId());
   }

   // Test adding a line item to a non-new order (should throw exception)
   @Test
   void testAddLineItemToNonNewOrder() {
      // Add line item to order
      order.addLineItem(product, quantity);

      // Place order
      order.place();

      // Add another line item to order (should throw exception)
      Product anotherProduct = new Product(
          new ProductId("P-789"),
          "Another Product",
          new Money(new BigDecimal("5.00"))
      );
      Quantity anotherQuantity = new Quantity(new BigDecimal("3"));
      assertThrows(
          IllegalStateException.class,
          () -> order.addLineItem(anotherProduct, anotherQuantity)
      );
   }

   // Test placing an empty order (should throw exception)
   @Test
   void testPlaceEmptyOrder() {
       // Place empty order (should throw exception)
       assertThrows(IllegalStateException.class, order::place);
   }

   // Test canceling a non-placed order (should throw exception)
   @Test
   void testCancelNonPlacedOrder() {
       // Cancel new order (should throw exception)
       assertThrows(IllegalStateException.class, order::cancel);
   }

   // Test shipping a non-placed order (should throw exception)
   @Test
   void testShipNonPlacedOrder() {
       // Ship new order (should throw exception)
       assertThrows(IllegalStateException.class, order::ship);
   }
}
