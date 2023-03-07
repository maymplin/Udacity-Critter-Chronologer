package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findCustomer(long customerId) {
        return customerRepository.findById(customerId).get();
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void addPet(Pet pet, Customer customer) {
        if (customer.getPets() == null) {
            List<Pet> pets = new ArrayList<>(
                    Arrays.asList(pet)
            );
            customer.setPets(pets);
        } else {
            customer.getPets().add(pet);
        }
    }
}
