package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee extends AbstractEntity {

    @NotBlank
    private String name;
    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvaialable;


}
