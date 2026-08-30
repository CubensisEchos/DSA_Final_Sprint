package com.keyin.orderBST;

import com.keyin.order.Order;

/**
 * Represents a node in the order priority BST
 * Stores an order and references its child nodes
 */
class OrderNode
{
    Order data;
    OrderNode left;
    OrderNode right;
    public OrderNode(Order data)
    {
        this.data = data;
    }
}