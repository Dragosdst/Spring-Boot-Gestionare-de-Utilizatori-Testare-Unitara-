package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String firstName;
    @Column(unique = true)
    private String email;
    private Integer age;


    public PersonEntity() {
    }


    public PersonEntity(Long id, String name, String firstName, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", nume='" + name + '\'' +
                ", prenume='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", varsta=" + age +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PersonEntity personEntity = (PersonEntity) obj;
        return Objects.equals(id, personEntity.id) &&
                Objects.equals(name, personEntity.name) &&
                Objects.equals(firstName, personEntity.firstName) &&
                Objects.equals(email, personEntity.email) &&
                Objects.equals(age, personEntity.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, firstName, email, age);
    }
}


//am adăugat o metodă de comparație a obiectelor în clasa Person. Această metodă compara toate proprietățile
// obiectului și returnează true dacă sunt egale și false în caz contrar.
//Această metodă va fi utilă în teste, pentru a verifica dacă obiectele Person sunt egale sau nu.







