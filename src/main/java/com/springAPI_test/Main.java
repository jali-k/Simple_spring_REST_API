package com.springAPI_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController // This ensures that all the mapping methods are endpoints
@RequestMapping("api/v1/customers") // Then, this is the path for get mapping
public class Main {
    // Create the customer repository
    private final CustomerRepository customerRepository;
    // Pass it as an argument to the main function
    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // The spring boot application
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Main & main:
    // main is the entry point of the program
    // Main is the method that represent the class Main

    // Get request
    @GetMapping
    public List getCutomers(){
        return customerRepository.findAll();
    }

    // A record is a data carrier class that is immutable. A canonical constructor is inbuilt there. And the toString(), hashcode(), equals() methods are overridden. Getters and setters are also defined. records can't extend other classes.
    record NewCustomerRequest(String name, String email, Integer age){

    }

    // Post request
    @PostMapping
    public void addNewCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);
    }

    // Deleting
    @DeleteMapping("{customer_id}")
    public void deleteCustomer(@PathVariable("customer_id") Integer id){
        customerRepository.deleteById(id);
    }

    // Updating
    @PutMapping("{customer_id}")
    public void updateCustomer(@PathVariable("customer_id") Integer id, @RequestBody NewCustomerRequest updateRequest){

        Customer updatingCustomer = customerRepository.findById(id).get();

        updatingCustomer.setName(updateRequest.name);
        updatingCustomer.setEmail(updateRequest.email);;
        updatingCustomer.setAge(updateRequest.age);
        customerRepository.save(updatingCustomer);
    }

}
