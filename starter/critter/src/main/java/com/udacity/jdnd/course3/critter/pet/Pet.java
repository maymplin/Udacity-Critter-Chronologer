package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.AbstractEntity;
import com.udacity.jdnd.course3.critter.user.Customer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pet extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private PetType type;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Customer customer;
    private LocalDate birthDate;
    private String notes;

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
