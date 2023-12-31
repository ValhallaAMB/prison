package com.example.prison.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prison.Entity.Cell;


public interface CellRepository extends JpaRepository<Cell, Long>{

    List<Cell> findByDistrictId(Long id);
}
