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

import com.example.prison.Entity.Cell;
import com.example.prison.Repository.CellRepository;
import com.example.prison.Repository.DistrictRepository;

@RestController
@RequestMapping("cell")
public class CellController {

    @Autowired
    CellRepository cellRep;

    @Autowired
    DistrictRepository districtRep;

    @GetMapping
    public List<Cell> getCells() {
        return cellRep.findAll();
    }

    @GetMapping("{id}")
    public Optional<Cell> getCell(@PathVariable Long id) {
        return cellRep.findById(id);
    }

    @PostMapping(value = "addCell", consumes = "application/json")
    public String addCell(@RequestBody Cell cell) {
        cellRep.save(cell);
        return "Cell Added";
    }

    @PostMapping(value = "addCellWithDistrict/{id}", consumes = "application/json")
    public String addCellWithDistrict(@RequestBody Cell cell, @PathVariable Long id) {
        cell.setDistrict(districtRep.findById(id).get());
        cellRep.save(cell);
        return "Cell Added Along With Its Assigned District";
    }

    @PostMapping(value = "addCells", consumes = "application/json")
    public String addCells(@RequestBody List<Cell> cell) {
        cellRep.saveAll(cell);
        return "Cells Added";
    }

    @PostMapping(value = "addCellsWithDistrict/{id}", consumes = "application/json")
    public String addCellWithDistricts(@RequestBody List<Cell> cells, @PathVariable Long id) {
        for (Cell cell : cells) {
            cell.setDistrict(districtRep.findById(id).get());
        }
        cellRep.saveAll(cells);
        return "Cells Added Along With Its Assigned District";
    }

    @GetMapping("assignDistrict/{cellId}/{disId}")
    public String assignDistrict(@PathVariable Long cellId, @PathVariable Long disId) {
        Cell cell = cellRep.findById(cellId).get();
        cell.setDistrict(districtRep.findById(disId).get());
        cellRep.save(cell);
        return "Cell Assigned To District";
    }

    @PutMapping("updateCell/{id}")
    public String updateCell(@RequestBody Cell cell, @PathVariable Long id) {
        Cell existingCell = cellRep.findById(id).get();
        existingCell.setCellType(cell.getCellType());
        cellRep.save(existingCell);
        return "Cell Updated";
    }

    @DeleteMapping("deleteCell/{id}")
    public String deleteCell(@PathVariable Long id) {
        cellRep.deleteById(id);
        return "Cell Deleted";
    } 

    @DeleteMapping("deleteAllCells")
    public String deleteAllCells() {
        cellRep.deleteAll();
        return "All Cells Deleted";
    }

    @GetMapping("getCellsByDistrict/{id}")
    public List<Cell> getCellsByDistrict(@PathVariable Long id){
        List<Cell> result = cellRep.findByDistrictId(id);
        return result;
    }
}


// this code is an alternative for the "getCellsByDistrict" function:
    /*@GetMapping("getCellsByDistrict/{id}")
    public List<Cell> getCellsByDistrict(@PathVariable Long id) {
        List<Cell> result = new ArrayList<>();
        for (Cell c : cellRep.findAll()) {
            if (c.getDistrict().getId().equals(id)) {
                result.add(c);
            }
        }
        return result;
    }*/
