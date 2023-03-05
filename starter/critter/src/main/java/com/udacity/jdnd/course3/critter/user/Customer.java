package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.AbstractEntity;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Customer extends AbstractEntity {

    @NotBlank
    @Nationalized
    private String name;
    private String phoneNumber;
    private String notes;
    @OneToMany(mappedBy = "customer")
    private List<Long> petIds;

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

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }
}
