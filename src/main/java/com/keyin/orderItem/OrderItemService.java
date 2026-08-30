package com.keyin.orderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing orderItem operations
 * Handles CRUD for orderItems
 */
@Service
public class OrderItemService
{
    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderItem addNewOrderItem(OrderItem orderItem)
    {
        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> getAllOrderItems()
    {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Long id)
    {
        return orderItemRepository.findById(id);
    }

    public Optional<OrderItem> updateOrderItem(Long id, OrderItem updatedOrderItem)
    {
        return orderItemRepository.findById(id).map(existing ->
        {
            existing.setQuantity(updatedOrderItem.getQuantity());
            return orderItemRepository.save(existing);
        });
    }

    public boolean deleteOrderItemById(Long id)
    {
        if (orderItemRepository.existsById(id))
        {
            orderItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
