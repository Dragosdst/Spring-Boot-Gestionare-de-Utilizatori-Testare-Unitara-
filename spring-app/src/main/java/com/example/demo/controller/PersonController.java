package com.example.demo.controller;

import com.example.demo.entity.PersonEntity;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/allPersons")
    public List<PersonEntity> getAllPersons() {
        return personService.getAllUsers();
    }


    @GetMapping("/{id}")
    public Object getUserById(@PathVariable Long id) {
        return personService.getUserById(id);
    }


    @PostMapping("/create")
    public ResponseEntity<PersonEntity> createPerson(@Valid @RequestBody PersonEntity person) {

        return ResponseEntity.status(HttpStatus.CREATED).body(personService.createPerson(person));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        personService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable Long id, @Valid @RequestBody PersonEntity updatedPerson) {
        String message = personService.updatePerson(id, updatedPerson);
        if (message.equals("Utilizatorul a fost actualizat cu succes.")) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}



