package com.example.prison.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Prisoner {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    @Column (name = "Name")
    String name;

    @Column (name = "Surname")
    String surname;

    @Column (name = "Age")
    int age;

    @Column (name = "YearsOfImprisonment")
    int yearsOfImprisonment;

    @Column (name = "CrimeType")
    String crimeType;

    @ManyToMany
    @JsonManagedReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<Labour> labour;

    @OneToOne
    @JsonManagedReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Cell cell;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getYearsOfImprisonment() {
        return yearsOfImprisonment;
    }

    public void setYearsOfImprisonment(int yearsOfImprisonment) {
        this.yearsOfImprisonment = yearsOfImprisonment;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }

    public List<Labour> getLabour() {
        return labour;
    }

    public void setLabour(List<Labour> labour) {
        this.labour = labour;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
