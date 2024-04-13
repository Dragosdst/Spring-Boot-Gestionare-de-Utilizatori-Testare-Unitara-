package com.example.demo.controller;

import com.example.demo.entity.PersonEntity;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonControllerTest {
    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllPersons() {
        // Definesc comportamentul așteptat al serviciului
        List<PersonEntity> expectedPersons = new ArrayList<>();
        when(personService.getAllUsers()).thenReturn(expectedPersons);

        // Apelez metoda controlorului și verific rezultatul
        List<PersonEntity> actualPersons = personController.getAllPersons();
        assertEquals(expectedPersons, actualPersons);
    }

    @Test
    void getUserById() {
        // Definesc comportamentul așteptat al serviciului
        Long id = 1L;
        PersonEntity expectedPerson = new PersonEntity();
        when(personService.getUserById(id)).thenReturn(Optional.of(expectedPerson));

        // Apelez metoda controlorului și verific rezultatul
        Object actualObject = personController.getUserById(id);
        // Extrag obiectul Person din Optional
        PersonEntity actualPerson = ((Optional<PersonEntity>) actualObject).orElse(null);
        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    void createPerson() {
        // Definesc un obiect PersonEntity pentru a fi creat
        PersonEntity personToCreate = new PersonEntity();
        // Definesc comportamentul așteptat al serviciului
        when(personService.createPerson(personToCreate)).thenReturn(personToCreate);

        // Apelez metoda controlorului și verific rezultatul
        ResponseEntity<PersonEntity> response = personController.createPerson(personToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(personToCreate, response.getBody());
    }

    @Test
    void deleteUser() {
        // Definesc id-ul utilizatorului care trebuie șters
        Long id = 1L;

        // Apelez metoda controlorului și verific că metoda corespunzătoare din serviciu este apelată
        personController.deleteUser(id);
        verify(personService, times(1)).deleteUser(id);
    }

    @Test
    void updatePerson() {
        // Definesc id-ul utilizatorului care trebuie actualizat și obiectul actualizat
        Long id = 1L;
        PersonEntity updatedPerson = new PersonEntity();

        // Definesc comportamentul așteptat al serviciului
        when(personService.updatePerson(id, updatedPerson)).thenReturn("Utilizatorul a fost actualizat cu succes.");

        // Apelez metoda controlorului și verificați rezultatul
        ResponseEntity<String> response = personController.updatePerson(id, updatedPerson);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Utilizatorul a fost actualizat cu succes.", response.getBody());
    }
}