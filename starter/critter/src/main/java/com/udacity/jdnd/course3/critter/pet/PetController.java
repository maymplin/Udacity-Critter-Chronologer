package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertPetDTOToEntity(petDTO);
        Customer owner = customerService.findCustomer(petDTO.getOwnerId());
        pet = petService.savePet(pet, owner);

        return convertEntityToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.findPet(petId);
        return convertEntityToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return convertEntityListToPetDTOList(petService.getPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return convertEntityListToPetDTOList(petService.getPetsByOwner(ownerId));
    }

//    -------- Helper methods: DTO Conversions -------

    private PetDTO convertEntityToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }

    private Pet convertPetDTOToEntity(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        pet.setCustomer(customerService.findCustomer(petDTO.getOwnerId()));
        return pet;
    }

    private List<PetDTO> convertEntityListToPetDTOList(List<Pet> pets) {
        return pets.stream()
                .map(pet -> convertEntityToPetDTO(pet))
                .collect(Collectors.toList());
    }

}
