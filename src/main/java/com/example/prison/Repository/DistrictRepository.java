package com.example.prison.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prison.Entity.District;

public interface DistrictRepository extends JpaRepository<District, Long> {

}
