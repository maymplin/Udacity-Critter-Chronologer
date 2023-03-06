package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findEmployee(Long id) {
        Optional<Employee> result = employeeRepository.findById(id);

        return result.get();
    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO requestDTO) {
        DayOfWeek dayOfWeek = requestDTO.getDate().getDayOfWeek();
        List<Employee> employees = employeeRepository.getAllByDaysAvailableContains(dayOfWeek);

        Set<EmployeeSkill> skills = requestDTO.getSkills();
        return employees.stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }







}
