package com.example.prison.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
    @JoinTable(name = "prisoner_labour",
            joinColumns = @JoinColumn(name = "prisoner_id"),
            inverseJoinColumns = @JoinColumn(name = "labour_id"))
    List<Labour> labour;

    @OneToOne
    Cell cell;

    // Getters and Setters

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
