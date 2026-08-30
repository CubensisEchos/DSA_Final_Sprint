package com.keyin.orderBST;

import com.keyin.order.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * BST for organizing orders by priority level
 * Lower values are stored in the left subtree, and higher ones in the right subtree
 */
public class OrderBST
{
    OrderNode root;

    /**
     * Inserts an order into BST based on priority level
     *
     * @param order the order being inserted
     */
    public void insert(Order order)
    {
        root = insertRecursive(root, order);
    }

    /**
     * Recursively inserts an order in the BST based on priority level
     *
     * @param current the current node being viewed
     * @param order the order being added
     * @return the updated node
     */
    private OrderNode insertRecursive(OrderNode current, Order order)
    {
        if (current == null)
        {
            return new OrderNode(order);
        }

        if (order.getPriorityLevel() < current.data.getPriorityLevel())
        {
            current.left = insertRecursive(current.left, order);
        }
        else if (order.getPriorityLevel() > current.data.getPriorityLevel())
        {
            current.right = insertRecursive(current.right, order);
        }
        else
        {
            current.right = insertRecursive(current.right, order);
        }
        return current;
    }

    /**
     * Returns all orders in ascending priority
     *
     * @return a list of orders sorted by priority level
     */
    public List<Order> inorder()
    {
        List<Order> orders = new ArrayList<>();
        inorderRecursive(root, orders);
        return orders;
    }

    /**
     * Recursively performs an inorder traversal of the BST
     *
     * @param node the current node being viewed
     * @param orders the list used to store traversal results
     */
    public void inorderRecursive(OrderNode node, List<Order> orders)
    {
        if (node == null)
        {
            return;
        }

        inorderRecursive(node.left, orders);
        orders.add(node.data);
        inorderRecursive(node.right, orders);
    }

    /**
     * Finds the order with the highest priority level
     *
     * @return the highest priority order, or null if tree is empty
     */
    public Order findHighest()
    {
        if (root == null)
        {
            return null;
        }

        OrderNode current = root;
        while (current.right != null)
        {
            current = current.right;
        }

        return current.data;
    }

    /**
     * Finds the order with the lowest priority level
     *
     * @return the lowest priority order, or null if tree is empty
     */
    public Order findLowest()
    {
        if (root == null)
        {
            return null;
        }

        OrderNode current = root;
        while (current.left !=null)
        {
            current =  current.left;
        }

        return current.data;
    }
}