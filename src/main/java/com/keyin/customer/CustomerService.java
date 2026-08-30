package com.keyin.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing customer operations
 * Handles CRUD for customers
 */
@Service
public class CustomerService
{
    @Autowired
    private CustomerRepository customerRepository;

    public Customer addNewCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id)
    {
        return customerRepository.findById(id);
    }

    public Optional<Customer> updateCustomer(Long id, Customer updatedCustomer)
    {
        return customerRepository.findById(id).map(existing ->
        {
            existing.setName(updatedCustomer.getName());
            existing.setEmail(updatedCustomer.getEmail());
            return customerRepository.save(existing);
        });
    }

    public boolean deleteCustomerById(Long id)
    {
        if (customerRepository.existsById(id))
        {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
