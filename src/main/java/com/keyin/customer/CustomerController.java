package com.keyin.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing customer resources
 * Provides CRUD endpoints for customer
 */
@RestController
@RequestMapping("/customers")
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer addNewCustomer(@RequestBody Customer customer)
    {
        return customerService.addNewCustomer(customer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers()
    {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id)
    {
        return customerService.getCustomerById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer)
    {
        return customerService.updateCustomer(id, customer)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id)
    {
        boolean deleted = customerService.deleteCustomerById(id);
        if (deleted)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
