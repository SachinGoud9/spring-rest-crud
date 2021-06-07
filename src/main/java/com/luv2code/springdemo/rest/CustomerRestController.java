package com.luv2code.springdemo.rest;


import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    //autowire the CusomerService
    @Autowired
    private CustomerService customerService;

    //add mapping for GET/Customers
    @GetMapping("/customers")
    public List<Customer> getCustomers(){

        return customerService.getCustomers();

    }

    //add mapping for GET/customers/{customerID}
    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){
        Customer theCustomer = customerService.getCustomer(customerId);

        if(theCustomer == null){
            throw new CustomerNotFoundException("Customer id not Found - "+customerId);
        }

        return theCustomer;
    }

    //add mapping for POST/customers - add new Customers
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer){

        //also just in case the ID in JSON set id is 0
        //this forces to save new customer instead of updating
        theCustomer.setId(0);

        customerService.saveCustomer(theCustomer);

        return theCustomer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer){

        //also just in case the ID in JSON set id is 0
        //this forces to save new customer instead of updating


        customerService.saveCustomer(theCustomer);

        return theCustomer;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId){
        Customer tempCustomer = customerService.getCustomer(customerId);

        if(tempCustomer == null){
            throw new CustomerNotFoundException("Customer id not Found - "+customerId);
        }

        customerService.deleteCustomer(customerId);

        return "Deleted customer id: "+customerId;
    }


}
