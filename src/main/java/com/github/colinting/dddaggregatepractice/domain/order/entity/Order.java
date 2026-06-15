package com.github.colinting.dddaggregatepractice.domain.order.entity;


import com.github.colinting.dddaggregatepractice.domain.order.valueobject.OrderId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 *
 * @author 丁浩
 * @version 2026年06月15 20:29
 */
@RequiredArgsConstructor
@Getter
public class Order {

    private final OrderId id;

//    private OrderStatus status;
//
//    private Money totalAmount;
//
//    private List<LineItem> lineItems;
}
