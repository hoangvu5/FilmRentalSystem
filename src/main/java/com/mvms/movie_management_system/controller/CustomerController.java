package com.mvms.movie_management_system.controller;

import com.mvms.movie_management_system.entity.Customer;
import com.mvms.movie_management_system.entity.Film;
import com.mvms.movie_management_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> fetchCustomers() {
        System.out.println("CustomerController - fetchCustomers()");
        return ResponseEntity.ok(customerService.getCustomers()).getBody();
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        System.out.println("CustomerController - createCustomers()");
        if (customer.getId() != null && customerService.getCustomerById(customer.getId()).isPresent()) {
            throw new IllegalArgumentException("Customer[id = " + customer.getId() + "] has already existed.");
        }
        return ResponseEntity.ok(customerService.createCustomer(customer)).getBody();
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        System.out.println("CustomerController - updateCustomers()");
        Optional<Customer> dbCustomer = customerService.getCustomerById(id);
        if (dbCustomer.isEmpty()) {
            throw new IllegalArgumentException("Customer[id = " + id + "] does not exist.");
        }
        else {
            return customerService.updateCustomer(id, customer);
        }
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        System.out.println("CustomerController - deleteCustomer()");
        Optional<Customer> dbCustomer = customerService.getCustomerById(id);
        if (dbCustomer.isEmpty()) {
            throw new IllegalArgumentException("Customer[id = " + id + "] does not exist.");
        } else {
            customerService.deleteCustomer(id);
            return "Customer[id = " + id + "] was deleted!";
        }
    }



}
