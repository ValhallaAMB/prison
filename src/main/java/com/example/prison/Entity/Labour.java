package com.example.prison.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Labour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "Labour Type")
    String labourType;

    @Column(name = "Labour Hours")
    int labourHours; 

    @ManyToMany(mappedBy = "labour", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Prisoner> prisoner;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabourType() {
        return labourType;
    }

    public void setLabourType(String labourType) {
        this.labourType = labourType;
    }

    public int getLabourHours() {
        return labourHours;
    }

    public void setLabourHours(int labourHours) {
        this.labourHours = labourHours;
    }

    public List<Prisoner> getPrisoner() {
        return prisoner;
    }

    public void setPrisoner(List<Prisoner> prisoner) {
        this.prisoner = prisoner;
    }

}
