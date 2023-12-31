package com.example.prison.Controller;

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

import com.example.prison.Entity.District;
import com.example.prison.Repository.DistrictRepository;

@RestController
@RequestMapping("district")
public class DistrictController {

    @Autowired
    DistrictRepository districtRep;

    @GetMapping
    public List<District> getDistricts() {
        return districtRep.findAll();
    }

    @GetMapping("{id}")
    public Optional<District> getDistrict(@PathVariable Long id) {
        return districtRep.findById(id);
    }

    @PostMapping(value = "addDistrict", consumes = "application/json")
    public String addDistrict(@RequestBody District district) {
        districtRep.save(district);
        return "District Added";
    }

    @PostMapping(value = "addDistricts"/* , consumes = "application/json"*/)
    public String addDistricts(@RequestBody List<District> district) {
        districtRep.saveAll(district);
        return "Districts Added";
    }

    @PutMapping("updateDistrict/{id}")
    public String updateDistrict(@RequestBody District district, @PathVariable Long id) {
        District existingDistrict = districtRep.findById(id).get();
        existingDistrict.setDistrictName(district.getDistrictName());
        existingDistrict.setDistrictLocation(district.getDistrictLocation());
        districtRep.save(existingDistrict);
        return "District Updated";
    }

    @DeleteMapping("deleteDistrict/{id}")
    public String deleteDistrict(@PathVariable Long id) {
        districtRep.deleteById(id);
        return "District Deleted";
    }

    @DeleteMapping("deleteAllDistricts")
    public String deleteAllDistricts() {
        districtRep.deleteAll();
        return "All Districts Deleted";
    }

    @GetMapping("getPrisonerByDistrict/{id}")
    public StringBuilder getPrisonerByDistrict(@PathVariable Long id) {
        StringBuilder result = new StringBuilder();
        districtRep.findById(id).get().getCell().stream()
            .forEach(c -> result.append(c.getPrisoner().getName() + " " + c.getPrisoner().getSurname() + " "
                        + c.getPrisoner().getAge() + " " + c.getPrisoner().getYearsOfImprisonment()
                        + " " + c.getPrisoner().getCrimeType() + "<br>"));

        return result;
    }
}
// this code is an alternative for the "getPrisonerByDistrict" function:
// c.getPrisoner().getName() + " " + c.getPrisoner().getSurname() + " "
//                         + c.getPrisoner().getAge() + " " + c.getPrisoner().getYearsOfImprisonment()
//                         + " " + c.getPrisoner().getCrimeType() + "<br>"
