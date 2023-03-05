package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.AbstractEntity;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee extends AbstractEntity {

    @NotBlank
    @Nationalized
    private String name;
    @NotEmpty
    @Enumerated
    private Set<EmployeeSkill> skills;
    @NotEmpty
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
