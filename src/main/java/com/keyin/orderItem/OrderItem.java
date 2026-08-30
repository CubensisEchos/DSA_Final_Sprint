package com.keyin.orderItem;

import com.keyin.order.Order;
import com.keyin.product.Product;
import jakarta.persistence.*;

/**
 * Represents an orderItem entity in the system
 * It stores orderItem info such as quantity
 */
@Entity
public class OrderItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem()
    {

    }

    public OrderItem(int quantity, Product product, Order order)
    {
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }

    public OrderItem(Long id, int quantity, Product product, Order order)
    {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }
}
