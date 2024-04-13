package com.example.demo.service;

import com.example.demo.entity.PersonEntity;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceTest {
    private PersonRepository personRepositoryMock;
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;


    // Inițializez mock-urile înainte de fiecare test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        personRepositoryMock = Mockito.mock(PersonRepository.class);
        personService = new PersonService();
        personService.setPersonRepository(personRepositoryMock); // Injectăm manual repository-ul în service

    }

    @Test
    void getAllUsers() {
        MockitoAnnotations.initMocks(this);

        List<PersonEntity> expectedPersons = new ArrayList<>();
        expectedPersons.add(new PersonEntity(1L, "John", "Doe", "john.doe@example.com", 30));
        expectedPersons.add(new PersonEntity(2L, "Jane", "Doe", "jane.doe@example.com", 28));

        Mockito.when(personRepository.findAll()).thenReturn(expectedPersons);

        List<PersonEntity> actualPersons = personService.getAllUsers();

        assertEquals(expectedPersons, actualPersons);

        Mockito.verify(personRepository, Mockito.times(1)).findAll();

    }


    @Test
    void getUserById() {
// Id-ul persoanei pentru care doresc să obțin informații
        Long userId = 1L;

        // Simulez obiectul PersonEntity returnat de repository
        PersonEntity expectedPerson = new PersonEntity(userId, "John", "Doe", "john.doe@example.com", 30);

        // Specific comportamentul așteptat al repository-ului mock atunci când este apelată metoda findById()
        when(personRepositoryMock.findById(userId)).thenReturn(Optional.of(expectedPerson));

        // Apelez metoda getUserById() din service
        Optional<PersonEntity> actualPersonOptional = personService.getUserById(userId);

        // Verific dacă metoda a returnat un obiect Optional<PersonEntity> nevid
        assertTrue(actualPersonOptional.isPresent(), "Expected person is present");

        // Obțin obiectul PersonEntity din Optional
        PersonEntity actualPerson = actualPersonOptional.get();

        // Verific dacă detaliile obținute sunt corecte
        assertEquals(expectedPerson, actualPerson, "Retrieved person details match expected");

    }


    @Test
    void createPerson() {
        // Simulez o persoană pe care doresc să o adaug
        PersonEntity personToAdd = new PersonEntity();
        personToAdd.setId(1L);
        personToAdd.setName("John");

        // Specific comportamentul așteptat al repository-ului mock
        when(personRepositoryMock.save(personToAdd)).thenReturn(personToAdd);

        // Apelez metoda createPerson din service
        PersonEntity createdPerson = personService.createPerson(personToAdd);

        // Verific dacă metoda a returnat corect persoana adăugată
        assertEquals(personToAdd, createdPerson);
    }

    @Test
    void deleteUser() {
        // Id-ul persoanei pe care doresc să o șterg
        Long userId = 1L;

        // Simulez obiectul PersonEntity asociat id-ului specificat
        PersonEntity personToDelete = new PersonEntity(userId, "John", "Doe", "john.doe@example.com", 30);

        // Specific comportamentul așteptat al repository-ului mock atunci când este apelată metoda findById()
        when(personRepositoryMock.findById(userId)).thenReturn(Optional.of(personToDelete));

        // Apelez metoda deleteUser() din service
        personService.deleteUser(userId);

        // Verific dacă metoda a fost apelată corect pe repository pentru a șterge persoana
        verify(personRepositoryMock, times(1)).deleteById(userId);
    }

    @Test
    void updatePerson() {
        // Id-ul persoanei pe care doresc să o actualizez
        Long userId = 1L;

        // Simulez obiectul PersonEntity existent în baza de date
        PersonEntity existingPerson = new PersonEntity(userId, "John", "Doe", "john.doe@example.com", 30);

        // Simulez obiectul PersonEntity actualizat
        PersonEntity updatedPerson = new PersonEntity(userId, "Jane", "Smith", "jane.smith@example.com", 35);

        // Specific comportamentul așteptat al repository-ului mock atunci când este apelată metoda findById()
        when(personRepositoryMock.findById(userId)).thenReturn(Optional.of(existingPerson));

        // Apelez metoda updatePerson() din service
        String message = personService.updatePerson(userId, updatedPerson);

        // Verific dacă mesajul întors de metoda updatePerson() este cel așteptat
        assertEquals("Utilizatorul a fost actualizat cu succes.", message);

        // Verific dacă numele, prenumele și emailul au fost actualizate corect în obiectul existingPerson
        assertEquals(updatedPerson.getName(), existingPerson.getName());
        assertEquals(updatedPerson.getFirstName(), existingPerson.getFirstName());
        assertEquals(updatedPerson.getEmail(), existingPerson.getEmail());

        // Verific dacă metoda save() a fost apelată pe repository pentru a actualiza obiectul
        verify(personRepositoryMock, times(1)).save(existingPerson);

    }
}