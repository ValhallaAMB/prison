package com.example.prison.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prison.Entity.Labour;
import com.example.prison.Repository.LabourRepository;

@RestController
@RequestMapping("labour")
public class LabourController {

    @Autowired
    LabourRepository labourRep;

    @GetMapping
    public List<Labour> getLabours() {
        return labourRep.findAll();
    }

    @GetMapping("{id}")
    public Optional<Labour> getLabour(@PathVariable Long id) {
        return labourRep.findById(id);
    }

    @PostMapping(value = "addLabour", consumes = "application/json")
    public String addLabour(@RequestBody Labour labour) {
        labourRep.save(labour);
        return "Labour Added";
    }

    @PostMapping(value = "addLabours", consumes = "application/json")
    public String addLabours(@RequestBody List<Labour> labour) {
        labourRep.saveAll(labour);
        return "Labour Added";
    }

    @PostMapping("updateLabour/{id}")
    public String updateLabour(@RequestBody Labour labour, @PathVariable Long id) {
        Labour existingLabour = labourRep.findById(id).get();
        existingLabour.setLabourHours(labour.getLabourHours());
        existingLabour.setLabourType(labour.getLabourType());
        labourRep.save(existingLabour);
        return "Labour Updated";
    }

    @DeleteMapping("deleteLabour/{id}")
    public String deleteLabour(@PathVariable Long id) {
        labourRep.deleteById(id);
        return "Labour Deleted";
    }

    @DeleteMapping("deleteAllLabours")
    public String deleteAllLabours() {
        labourRep.deleteAll();
        return "All Labours Deleted";
    }
}
