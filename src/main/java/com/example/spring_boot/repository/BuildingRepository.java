package com.example.spring_boot.repository;

import com.example.spring_boot.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, String> {
}
