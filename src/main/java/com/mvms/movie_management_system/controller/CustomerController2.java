package com.mvms.movie_management_system.controller;

import com.mvms.movie_management_system.entity.Customer;
import com.mvms.movie_management_system.service.CustomerService;
import com.mvms.movie_management_system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
    public class CustomerController2 {

        @Autowired
        private CustomerService customerService;
        @Autowired
        private CustomerRepository customerRepository;

        @GetMapping("/customers2")
        public String getAll(Model model, @Param("keyword") String keyword) {
            try {
                List<Customer> customers = new ArrayList<>();
                if (keyword == null) {
                    customerRepository.findAll().forEach(customers::add);
                } else {
                    customerRepository.findByFirstName(keyword).forEach(customers::add);
                    model.addAttribute("keyword", keyword);
                }
                model.addAttribute("customers", customers);
            } catch (Exception e) {
                model.addAttribute("message", e.getMessage());
            }
            return "customers2";
        }

        @GetMapping("/customers2/new")
        public String addCustomer(Model model) {
            Customer customer = new Customer();

            model.addAttribute("customer", customer);
            model.addAttribute("pageTitle", "Create new Tutorial");

            return "customers2-form";
        }

        @PostMapping("/customers2/save")
        public String saveCustomer(Customer customer, RedirectAttributes redirectAttributes) {
            try{
                customerRepository.save(customer);
                redirectAttributes.addAttribute("message", "Customer saved");
            }catch(Exception e){
                redirectAttributes.addFlashAttribute("message", e.getMessage());
            }
            return "redirect:/customers2";
        }

        @GetMapping("/customers2/{id}")
        public String editCustomer(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
            try{
                Customer customer = customerRepository.findById(Long.valueOf(id)).get();
                model.addAttribute("customer", customer);
                model.addAttribute("pageTitle", "Create new Tutorial");
                return "customers2-form";
            } catch(Exception e){
                redirectAttributes.addFlashAttribute("message", e.getMessage());
                return "redirect:/customers2";
            }
        }

        @GetMapping("/customers2/delete/{id}")
        public String deleteCustomer(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
            try{
                customerRepository.deleteById(Long.valueOf(id));
                redirectAttributes.addFlashAttribute("message", "The Tutorial with id=" + id + " has been deleted successfully!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("message", e.getMessage());
            }
            return "redirect:/customers2";
        }
    }
