package com.github.colinting.dddaggregatepractice.domain.order.support;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 *
 *
 * @author 丁浩
 * @version 2026年06月19 14:22
 */
public final class DomainEvents {

    private static final List<Consumer<Object>> HANDLERS = new CopyOnWriteArrayList<>();

    private DomainEvents() {
    }

    public static void publish(Object event) {
        if (event == null) {
            throw new IllegalArgumentException("Domain event cannot be null");
        }

        for (Consumer<Object> handler : HANDLERS) {
            handler.accept(event);
        }
    }

    public static void register(Consumer<Object> handler) {
        if (handler == null) {
            throw new IllegalArgumentException("Domain event handler cannot be null");
        }

        HANDLERS.add(handler);
    }

    public static void clearHandlers() {
        HANDLERS.clear();
    }
}
