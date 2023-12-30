package com.example.prison.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prison.Entity.Prisoner;

public interface PrisonerRepository extends JpaRepository<Prisoner, Long>{

    List<Prisoner> findByCellId(Long id);
    List<Prisoner> findByLabourId(Long id);
}
