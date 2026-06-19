package com.github.colinting.dddaggregatepractice.domain.order.entity;


import com.github.colinting.dddaggregatepractice.domain.order.valueobject.Money;
import com.github.colinting.dddaggregatepractice.domain.order.valueobject.ProductId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 *
 * @author 丁浩
 * @version 2026年06月19 14:05
 */
@Getter
@RequiredArgsConstructor
public class Product {

    private final ProductId id;

    private final String name;

    private final Money price;

}
