package com.keyin.order;

import com.keyin.orderBST.OrderBST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing order operations
 * Handles CRUD and priority using BST
 */
@Service
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;

    private OrderBST orderBST = new OrderBST();

    public Order addNewOrder(Order order)
    {
        if (order.getPriorityLevel() < 1 || order.getPriorityLevel() > 10)
        {
            throw new IllegalArgumentException("Priority level must be between 1 and 10");
        }
        Order newOrder = orderRepository.save(order);
        orderBST.insert(newOrder);
        return newOrder;
    }

    public List<Order> getAllOrders()
    {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id)
    {
        return orderRepository.findById(id);
    }

    public Optional<Order> updateOrder(Long id, Order updatedOrder)
    {
        if (updatedOrder.getPriorityLevel()< 1 || updatedOrder.getPriorityLevel() > 10)
        {
            throw new IllegalArgumentException("Priority level must be between 1 and 10");
        }

        return orderRepository.findById(id).map(existing ->
        {
            existing.setOrderDate(updatedOrder.getOrderDate());
            existing.setPriorityLevel(updatedOrder.getPriorityLevel());

            Order updated = orderRepository.save(existing);
            orderBST = new OrderBST();

            for (Order order : orderRepository.findAll())
            {
                 orderBST.insert(order);
            }
            return updated;
        });
    }

    public boolean deleteOrderById(Long id)
    {
        if (orderRepository.existsById(id))
        {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Order> getPriorityInorder()
    {
        return orderBST.inorder();
    }

    public Optional<Order> getHighestPriority()
    {
        return Optional.ofNullable(orderBST.findHighest());
    }

    public Optional<Order> getLowestPriority()
    {
        return Optional.ofNullable(orderBST.findLowest());
    }
}
