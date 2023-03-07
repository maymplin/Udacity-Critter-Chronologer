package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PetService petService;

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule findSchedule(long scheduleId) {
        return scheduleRepository.findById(scheduleId).get();
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(long petId) {
        return getAllSchedules().stream()
                .filter(schedule ->
                        schedule.getPets().contains(petService.findPet(petId)))
                .collect(Collectors.toList());
    }

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        return getAllSchedules().stream()
                .filter(schedule ->
                        schedule.getEmployees().contains(employeeService.findEmployee(employeeId)))
                .collect(Collectors.toList());
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        List<Pet> pets = petService.getPetsByOwner(customerId);

        List<Schedule> schedules = new ArrayList<>();

        for (Pet pet : pets) {
            schedules.addAll(getScheduleForPet(pet.getId()));
        }

        return schedules;
    }

}
