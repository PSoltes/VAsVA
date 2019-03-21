package vava.soltesvasko.lezeckastena.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Users {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(name = "date_of_birth", columnDefinition = "DATE")
    private LocalDate dateOfBirth;

    @Column(name = "registration_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime registrationDate;

    public Users() {
        super();
    }

    public Users(Long id, String name, LocalDate dateOfBirth, LocalDateTime registrationDate) {
        super();
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = LocalDateTime.now();
    }

    public Users(String name) {
        super();
        this.name = name;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return String.format("Users [id=%s, name=%s]", id, name);
    }

}

