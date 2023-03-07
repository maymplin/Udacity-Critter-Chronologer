package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertScheduleDTOToEntity(scheduleDTO);
        schedule = scheduleService.saveSchedule(schedule);
        return convertEntityToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();

        return convertEntityListToScheduleDTOList(schedules);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getScheduleForPet(petId);

        return convertEntityListToScheduleDTOList(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);

        return convertEntityListToScheduleDTOList(schedules);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getScheduleForCustomer(customerId);

        return convertEntityListToScheduleDTOList(schedules);
    }

//    -------- Helper methods: DTO Conversions -------

    private Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        List<Long> petIds = scheduleDTO.getPetIds();

        BeanUtils.copyProperties(scheduleDTO, schedule);

        if (employeeIds != null) {
            List<Employee> employees = employeeIds.stream()
                    .map(id -> employeeService.findEmployee(id))
                    .collect(Collectors.toList());
            schedule.setEmployees(employees);
        }

        if (petIds != null) {
            List<Pet> pets = petIds.stream()
                    .map(id -> petService.findPet(id))
                    .collect(Collectors.toList());
            schedule.setPets(pets);
        }

        return schedule;
    }

    private ScheduleDTO convertEntityToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        List<Employee> employees = schedule.getEmployees();
        List<Pet> pets = schedule.getPets();

        BeanUtils.copyProperties(schedule, scheduleDTO);

        if (employees != null) {
            List<Long> employeeIds = employees.stream()
                    .map(employee -> employee.getId())
                    .collect(Collectors.toList());
            scheduleDTO.setEmployeeIds(employeeIds);
        }

        if (pets != null) {
            List<Long> petIds = pets.stream()
                    .map(pet -> pet.getId())
                    .collect(Collectors.toList());
            scheduleDTO.setPetIds(petIds);
        }

        return scheduleDTO;
    }

    public List<ScheduleDTO> convertEntityListToScheduleDTOList(List<Schedule> schedules) {
        return schedules.stream()
                .map(schedule -> convertEntityToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }
}
