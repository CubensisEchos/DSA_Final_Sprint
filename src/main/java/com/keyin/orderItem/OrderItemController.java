package com.keyin.orderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing orderItem resources
 * Provides CRUD endpoints for orderItems
 */
@RestController
@RequestMapping("/orderItem")
public class OrderItemController
{
    @Autowired
    private OrderItemService orderItemService;

    @PostMapping
    public OrderItem addNewOrderItem(@RequestBody OrderItem orderItem)
    {
        return orderItemService.addNewOrderItem(orderItem);
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems()
    {
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id)
    {
        return orderItemService.getOrderItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem)
    {
        return orderItemService.updateOrderItem(id, orderItem)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id)
    {
        boolean deleted = orderItemService.deleteOrderItemById(id);
        if (deleted)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
