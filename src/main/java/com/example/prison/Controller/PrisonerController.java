package com.example.prison.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prison.Entity.Labour;
import com.example.prison.Entity.Prisoner;
import com.example.prison.Repository.CellRepository;
import com.example.prison.Repository.LabourRepository;
import com.example.prison.Repository.PrisonerRepository;

@RestController
@RequestMapping("prisoner")
public class PrisonerController {

    @Autowired
    PrisonerRepository prisonerRep;

    @Autowired
    CellRepository CellRep;

    @Autowired
    LabourRepository labourRep;

    @GetMapping
    public List<Prisoner> getPrisoners() {
        return prisonerRep.findAll();
    }

    @GetMapping("{id}")
    public Optional<Prisoner> getPrisoner(@PathVariable Long id) {
        return prisonerRep.findById(id);
    }

    @PostMapping(value = "addPrisoner", consumes = "application/json")
    public String addPrisoner(@RequestBody Prisoner prisoner) {
        prisonerRep.save(prisoner);
        return "Prisoner Added";
    }

    @PostMapping(value = "addPrisonerWithLabour/{id}", consumes = "application/json")
    public String addPrisonerWithLabour(@RequestBody Prisoner prisoner, @PathVariable Long id) {
        if (prisoner.getLabour() == null) {
            prisoner.setLabour(new ArrayList<Labour>());
        }
        prisoner.getLabour().add(labourRep.findById(id).get());
        prisonerRep.save(prisoner);
        return "Prisoner Added With Their Corresponding Labour";
    }

    @PostMapping(value = "addPrisonerWithCell/{id}", consumes = "application/json")
    public String addPrisonerWithCell(@RequestBody Prisoner prisoner, @PathVariable Long id) {
        prisoner.setCell(CellRep.findById(id).get());
        prisonerRep.save(prisoner);
        return "Prisoner Added With Their Corresponding Cell";
    }

    @PostMapping(value = "addPrisonerWithCellAndLabour/{cellId}/{labId}", consumes = "application/json")
    public String addPrisonerWithCellAndLabour(@RequestBody Prisoner prisoner, @PathVariable Long cellId,
            @PathVariable Long labId) {
        prisoner.setCell(CellRep.findById(cellId).get());
        if (prisoner.getLabour() == null) {
            prisoner.setLabour(new ArrayList<Labour>());
        }
        prisoner.getLabour().add(labourRep.findById(labId).get());
        prisonerRep.save(prisoner);
        return "Prisoner Added With Their Corresponding Cell And Labour";
    }

    @PostMapping(value = "addPrisoners", consumes = "application/json")
    public String addPrisoners(@RequestBody List<Prisoner> prisoner) {
        prisonerRep.saveAll(prisoner);
        return "Prisoner Added";
    }

    @PostMapping(value = "addPrisonersWithLabour/{id}", consumes = "application/json")
    public String addPrisonersWithLabour(@RequestBody List<Prisoner> prisoner, @PathVariable Long id) {
        for (Prisoner p : prisoner) {
            if (p.getLabour() == null) {
                p.setLabour(new ArrayList<Labour>());
            }
            
            p.getLabour().add(labourRep.findById(id).get());
        }
        prisonerRep.saveAll(prisoner);
        return "Prisoner Added With Their Corresponding Labour";
    }

    @GetMapping("assignCell/{priId}/{cellId}")
    public String assignCell(@PathVariable Long priId, @PathVariable Long cellId) {
        Prisoner prisoner = prisonerRep.findById(priId).get();
        prisoner.setCell(CellRep.findById(cellId).get());
        prisonerRep.save(prisoner);
        return "Prisoner Assigned To Cell";
    }

    @GetMapping("assignLabour/{priId}/{labId}")
    public String assignLabour(@PathVariable Long priId, @PathVariable Long labId) {
        Prisoner prisoner = prisonerRep.findById(priId).get();
        prisoner.getLabour().add(labourRep.findById(labId).get());
        prisonerRep.save(prisoner);
        return "Prisoner Assigned To Labour";
    }

    @PutMapping("updatePrisoner/{id}")
    public String updatePrisoner(@RequestBody Prisoner prisoner, @PathVariable Long id) {
        Prisoner existingPrisoner = prisonerRep.findById(id).get();
        existingPrisoner.setName(prisoner.getName());
        existingPrisoner.setSurname(prisoner.getSurname());
        existingPrisoner.setAge(prisoner.getAge());
        existingPrisoner.setYearsOfImprisonment(prisoner.getYearsOfImprisonment());
        existingPrisoner.setCrimeType(prisoner.getCrimeType());
        prisonerRep.save(existingPrisoner);
        return "Prisoner Updated";
    }

    @DeleteMapping("deletePrisoner/{id}")
    public String deletePrisoner(@PathVariable Long id) {
        Prisoner prisoner = prisonerRep.findById(id).orElse(null);
        if (!prisonerRep.findById(id).equals(null)) {
            prisoner.setCell(null);
            prisonerRep.save(prisoner);
            prisonerRep.delete(prisoner);
        }
        return "Prisoner Deleted";
    }

    @DeleteMapping("deleteAllPrisoners")
    public String deleteAllPrisoners() {
        prisonerRep.deleteAll();
        return "All Prisoners Deleted";
    }

    @GetMapping("getPrisonersRelatedToLabour")
    public StringBuilder getPrisonersRelatedToLabour() {

        StringBuilder result = new StringBuilder();
        prisonerRep.findAll().stream().filter(p -> !p.getLabour().isEmpty()).forEach(p -> result.append("Name: "
                + p.getName() + " " + p.getSurname() + ". Age:" + p.getAge() + ". Years of imprisonment: "
                + p.getYearsOfImprisonment() + ". Crime Type: " + p.getCrimeType() + ". <br>" + "Labour Type:"
                + p.getLabour().get(0).getLabourType() + ", " + p.getLabour().get(0).getLabourHours()
                + " Hours. <br>"));

        return result;
    }

    @GetMapping("getPrisonersByCell/{id}")
    public List<Prisoner> getPrisonersByCell(@PathVariable Long id) {
        List<Prisoner> result = prisonerRep.findByCellId(id);
        return result;
    }

    @GetMapping("getPrisonersByLabour/{id}")
    public List<Prisoner> getPrisonersByLabour(@PathVariable Long id) {
        List<Prisoner> result = prisonerRep.findByLabourId(id);
        return result;
    }
}

//this is code is an alternative for the "getPrisonersRelatedToLabour" function:
// String result = "Prisoners Related To Labour: <br>";
        // for (Prisoner p : prisonerRep.findAll()) {

        // if (!p.getLabour().isEmpty()) {

        // result += "Name: " + p.getName() + " " + p.getSurname() + ". Age:" +
        // p.getAge()
        // + ". Years of imprisonment: " + p.getYearsOfImprisonment() + ". Crime Type: "
        // + p.getCrimeType() + ". <br>";
        // for (Labour l : p.getLabour()) {
        // result += "Labour Type:" + l.getLabourType() + ", " + l.getLabourHours() + "
        // Hours. <br>";
        // }
        // }
        // }