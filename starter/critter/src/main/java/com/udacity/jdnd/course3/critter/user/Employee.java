package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.AbstractEntity;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee extends AbstractEntity {

    @Column(nullable = false)
    @Nationalized
    private String name;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "id"))
    private Set<EmployeeSkill> skills;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "id"))
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
