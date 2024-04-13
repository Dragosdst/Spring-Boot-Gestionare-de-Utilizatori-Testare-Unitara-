package com.example.demo.service;

import com.example.demo.entity.PersonEntity;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public List<PersonEntity> getAllUsers() {
        return personRepository.findAll();
    }


    public Optional<PersonEntity> getUserById(Long id) {
        return personRepository.findById(id);
    }


    public PersonEntity createPerson(PersonEntity person) {
        return personRepository.save(person);
    }


    public void deleteUser(Long id) {
        Optional<PersonEntity> existingPersonOptional = personRepository.findById(id);
        if (existingPersonOptional.isPresent()) {
            personRepository.deleteById(id);

        } else {
            throw new IllegalArgumentException("Utilizatorul " + id + " nu a fost gasit");

        }
    }

    public String updatePerson(Long id, PersonEntity updatedPerson) {
        Optional<PersonEntity> existingPersonOptional = personRepository.findById(id);
        if (existingPersonOptional.isPresent()) {
            PersonEntity existingPerson = existingPersonOptional.get();

            // Verificăm dacă obiectul actualizat nu este null
            if (updatedPerson != null) {
                // Verificăm și actualizăm numele, dacă este diferit de null și nu este gol
                if (updatedPerson.getName() != null && !updatedPerson.getName().isEmpty()) {
                    existingPerson.setName(updatedPerson.getName());
                }
                // Verificăm și actualizăm prenumele, dacă este diferit de null și nu este gol
                if (updatedPerson.getFirstName() != null && !updatedPerson.getFirstName().isEmpty()) {
                    existingPerson.setFirstName(updatedPerson.getFirstName());
                }
                // Verificăm și actualizăm emailul, dacă este diferit de null și nu este gol
                if (updatedPerson.getEmail() != null && !updatedPerson.getEmail().isEmpty()) {
                    existingPerson.setEmail(updatedPerson.getEmail());
                }
                // Verificăm și actualizăm varsta, dacă este diferit de null
                if (updatedPerson.getAge() != null) {
                    existingPerson.setAge(updatedPerson.getAge());
                }

                // Salvăm modificările în baza de date
                personRepository.save(existingPerson);
                return "Utilizatorul a fost actualizat cu succes.";
            } else {
                return "Obiectul actualizat este null.";
            }
        } else {
            return "Utilizatorul nu a fost găsit.";
        }
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
//
//    public PersonRepository getPersonRepository() {
//        return personRepository;
//    }
//}
}

