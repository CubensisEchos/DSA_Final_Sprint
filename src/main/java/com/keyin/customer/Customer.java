package com.keyin.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keyin.order.Order;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer entity in the system
 * It stores customer information like name and email
 */
@Entity
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    public Customer()
    {

    }

    public Customer(String name, String email, List<Order> orders)
    {
        this.name = name;
        this.email = email;
        this.orders = orders;
    }

    public Customer(Long id, String name, String email, List<Order> orders)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.orders = orders;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(List<Order> orders)
    {
        this.orders = orders;
    }
}
