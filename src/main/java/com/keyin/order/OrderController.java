package com.keyin.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing order resources
 * Provides endpoints for CRUD and BST priority
 */
@RestController
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order addNewOrder(@RequestBody Order order)
    {
        return orderService.addNewOrder(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders()
    {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id)
    {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order)
    {
        return orderService.updateOrder(id, order)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id)
    {
        boolean deleted = orderService.deleteOrderById(id);
        if (deleted)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/priority/inorder")
    public List<Order> getPriorityInorder()
    {
        return orderService.getPriorityInorder();
    }

    @GetMapping("/priority/highest")
    public ResponseEntity<Order> getHighestPriority()
    {
        return orderService.getHighestPriority().map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/priority/lowest")
    public ResponseEntity<Order> getLowestPriority()
    {
        return orderService.getLowestPriority().map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
