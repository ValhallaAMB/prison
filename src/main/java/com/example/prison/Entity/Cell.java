package com.example.prison.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Cell {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;

    @Column (name = "Cell Type")
    String cellType;

    @OneToOne (mappedBy = "cell", cascade = CascadeType.ALL)
    // @JsonBackReference is used to handle the bidirectional relationship.
    @JsonBackReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Prisoner prisoner;

    @ManyToOne
    @JsonManagedReference
    //This annotation tells Jackson (the library used for JSON serialization in Spring) not to serialize the non-owning property when serializing a Cell.
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) 
    District district;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    public Prisoner getPrisoner() {
        return prisoner;
    }

    public void setPrisoner(Prisoner prisoner) {
        this.prisoner = prisoner;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
