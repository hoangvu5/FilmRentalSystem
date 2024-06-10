package com.mvms.movie_management_system.service;

import com.mvms.movie_management_system.entity.Customer;
import com.mvms.movie_management_system.entity.Gender;
import com.mvms.movie_management_system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    public List<Customer> getCustomersByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    public List<Customer> getCustomersByGender(Gender gender) {
        return customerRepository.findByGender(gender);
    }

    public List<Customer> getCustomersByActive(boolean active) {
        return customerRepository.findByActive(active);
    }

    public Optional<Customer> getCustomerByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

//    public Optional<Customer> getCustomerByEmailAddress(String emailAddress) {
//        return customerRepository.findByEmailAddress(emailAddress);
//    }

    // Update an existing customer
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        if (customerRepository.findById(id).isPresent()) {
            Customer existingCustomer = customerRepository.findById(id).get();
            existingCustomer.setFirstName(updatedCustomer.getFirstName());
            existingCustomer.setLastName(updatedCustomer.getLastName());
            existingCustomer.setGender(updatedCustomer.getGender());
            existingCustomer.setActive(updatedCustomer.isActive());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            existingCustomer.setPhones(updatedCustomer.getPhones());
            existingCustomer.setOrders(updatedCustomer.getOrders());
            existingCustomer.setRentals(updatedCustomer.getRentals());
            existingCustomer.setLastUpdate(updatedCustomer.getLastUpdate());

            return customerRepository.save(existingCustomer);
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }

    // Delete a customer by ID
    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }
}
