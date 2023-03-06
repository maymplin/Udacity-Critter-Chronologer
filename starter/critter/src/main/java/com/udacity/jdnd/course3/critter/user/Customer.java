package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.AbstractEntity;
import com.udacity.jdnd.course3.critter.pet.Pet;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Customer extends AbstractEntity {

    @Column(nullable = false)
    @Nationalized
    private String name;
    private String phoneNumber;
    private String notes;
    @OneToMany(mappedBy = "customer")
    private List<Pet> pets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
