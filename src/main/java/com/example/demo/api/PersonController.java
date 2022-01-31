package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
//RequestMapping to define the path - to denote the URL to be mapped - to create endpoint with Spring-boot
//URL from where the data is sent to backend & to where data is accepted from backend
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    //@RequestBody to denote that body needs to be requested from the Client
    public void addPerson(@Valid @NotNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping //To tell that this method will be served as a Get Request
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}") //To have the actual ID in the Path
    //To get ID from the Path, we use the annotation @PathVariable
    public Person getPersonById(@PathVariable("id") UUID id) { //Grabbing the ID here & converting that to UUID
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}") //Deleting a resource from the server
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePersonById(id);
    }

    @PutMapping(path = "{id}")
    public void updatePersonById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person newPerson) {
        personService.updatePersonById(id, newPerson);
    }
}
