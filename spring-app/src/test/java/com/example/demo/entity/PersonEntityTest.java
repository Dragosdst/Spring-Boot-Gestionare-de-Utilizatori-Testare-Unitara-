package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonEntityTest {

    @Test
    void getId() {
        // Creez un obiect PersonEntity și setez ID-ul
        PersonEntity person = new PersonEntity();
        person.setId(1L);

        // Verific dacă getId() întoarce corect ID-ul
        assertEquals(1L, person.getId());
    }

    @Test
    void setId() {
        // Creez un obiect PersonEntity și setez ID-ul
        PersonEntity person = new PersonEntity();
        person.setId(1L);

        // Verific dacă setarea ID-ului a funcționat corect
        assertEquals(1L, person.getId());
    }

    @Test
    void getName() {
        // Crez un obiect PersonEntity și setez numele
        PersonEntity person = new PersonEntity();
        person.setName("John");

        // Verific dacă getNume() întoarce corect numele
        assertEquals("John", person.getName());
    }

    @Test
    void setName() {
        // Creez un obiect PersonEntity și setez numele
        PersonEntity person = new PersonEntity();
        person.setName("John");

        // Verific dacă setarea numelui a funcționat corect
        assertEquals("John", person.getName());
    }

    @Test
    void getFirstName() {
        // Creez un obiect PersonEntity și setez prenumele
        PersonEntity person = new PersonEntity();
        person.setFirstName("Doe");

        // Verific dacă getPrenume() întoarce corect prenumele
        assertEquals("Doe", person.getFirstName());
    }

    @Test
    void setFirstName() {
        // Creez un obiect PersonEntity și setez prenumele
        PersonEntity person = new PersonEntity();
        person.setFirstName("Doe");

        // Verific dacă setarea prenumelui a funcționat corect
        assertEquals("Doe", person.getFirstName());
    }

    @Test
    void getEmail() {
        // Creez un obiect PersonEntity și setez emailul
        PersonEntity person = new PersonEntity();
        person.setEmail("john.doe@example.com");

        // Verific dacă getEmail() întoarce corect emailul
        assertEquals("john.doe@example.com", person.getEmail());
    }

    @Test
    void setEmail() {
        // Creez un obiect PersonEntity și setez emailul
        PersonEntity person = new PersonEntity();
        person.setEmail("john.doe@example.com");

        // Verific dacă setarea emailului a funcționat corect
        assertEquals("john.doe@example.com", person.getEmail());
    }

    @Test
    void getAge() {
        // Creez un obiect PersonEntity și setez varsta
        PersonEntity person = new PersonEntity();
        person.setAge(30);

        // Verific dacă getVarsta() întoarce corect varsta
        assertEquals(30, person.getAge());
    }

    @Test
    void setAge() {
        // Creez un obiect PersonEntity și setez varsta
        PersonEntity person = new PersonEntity();
        person.setAge(30);

        // Verific dacă setarea varstei a funcționat corect
        assertEquals(30, person.getAge());
    }

    @Test
    void testToString() {
        // Creez un obiect PersonEntity pentru testare
        PersonEntity person = new PersonEntity(1L, "John", "Doe", "john.doe@example.com", 30);

        // Verific corectitudinea metodei toString()
        assertEquals("Person{id=1, nume='John', prenume='Doe', email='john.doe@example.com', varsta=30}", person.toString());
    }

    @Test
    void testEquals() {
        PersonEntity person1 = new PersonEntity(1L, "John", "Doe", "john.doe@example.com", 30);
        PersonEntity person2 = new PersonEntity(1L, "John", "Doe", "john.doe@example.com", 30);

        // Verific dacă sunt egali
        assertEquals(person1, person2);
    }

    @Test
    void testHashCode() {
        // Creez doi utilizatori cu aceleași atribute
        PersonEntity person1 = new PersonEntity(1L, "John", "Doe", "john.doe@example.com", 30);
        PersonEntity person2 = new PersonEntity(1L, "John", "Doe", "john.doe@example.com", 30);

        // Verific dacă hash code-urile sunt egale
        assertEquals(person1.hashCode(), person2.hashCode());

    }

}