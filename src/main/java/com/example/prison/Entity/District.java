package com.example.prison.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "District Name")
    String districtName;

    @Column(name = "District Location")
    String districtLocation;

    // One to Many Relationship with Cell
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    // This indicate that Jackson should not serialize this property to avoid
    // infinite recursion during the serialization process.
    @JsonBackReference
    List<Cell> cell;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictLocation() {
        return districtLocation;
    }

    public void setDistrictLocation(String districtLocation) {
        this.districtLocation = districtLocation;
    }

    public List<Cell> getCell() {
        return cell;
    }

    public void setCell(List<Cell> cell) {
        this.cell = cell;
    }

}
