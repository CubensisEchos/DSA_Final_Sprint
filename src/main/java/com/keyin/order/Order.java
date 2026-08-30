package com.keyin.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keyin.customer.Customer;
import com.keyin.orderItem.OrderItem;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order entity in the system
 * It stores information like orderDate and priorityLevel
 */
@Entity
@Table(name ="`order`")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate orderDate;
    private int priorityLevel;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order()
    {

    }

    public Order(LocalDate orderDate, int priorityLevel, Customer customer, List<OrderItem> orderItems)
    {
        this.orderDate = orderDate;
        this.priorityLevel = priorityLevel;
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public Order(Long id, LocalDate orderDate, int priorityLevel, Customer customer, List<OrderItem> orderItems)
    {
        this.id = id;
        this.orderDate = orderDate;
        this.priorityLevel = priorityLevel;
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public LocalDate getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate)
    {
        this.orderDate = orderDate;
    }

    public int getPriorityLevel()
    {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel)
    {
        this.priorityLevel = priorityLevel;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems()
    {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems)
    {
        this.orderItems = orderItems;
    }
}
