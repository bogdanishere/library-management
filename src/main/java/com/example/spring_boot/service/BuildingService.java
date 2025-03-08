package com.example.spring_boot.service;

import com.example.spring_boot.dto.Building.CreateBuildingDTO;
import com.example.spring_boot.model.Building;
import com.example.spring_boot.model.User;
import com.example.spring_boot.repository.BuildingRepository;
import com.example.spring_boot.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuildingService {
    private final BuildingRepository buildingRepository;
    private final UserRepository userRepository;

    public BuildingService(BuildingRepository buildingRepository, UserRepository userRepository) {
        this.buildingRepository = buildingRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<Building> getBuildings() {
        return buildingRepository.findAll();
    }

    @Transactional
    public Building getBuildingById(String id) {
        return buildingRepository.findById(id).orElse(null);
    }

    @Transactional
    public User createBuilding(CreateBuildingDTO building) {
        User existingUser = userRepository.findById(building.getAdminId()).orElse(null);

        if(existingUser == null || !existingUser.getAdmin()) {
            throw new RuntimeException("Admin not found!");
        }

        Building newBuilding = new Building();
        newBuilding.setName(building.getName());

        buildingRepository.save(newBuilding);

        return existingUser;

    }

    @Transactional
    public User updateBuilding(String buildingId, CreateBuildingDTO building) {
        User existingUser = userRepository.findById(building.getAdminId()).orElse(null);

        if(existingUser == null || !existingUser.getAdmin()) {
            throw new RuntimeException("Admin not found!");
        }

        Building existingBuilding = buildingRepository.findById(buildingId).orElse(null);

        if(existingBuilding == null) {
            throw new RuntimeException("Building not found!");
        }

        existingBuilding.setName(building.getName());

        buildingRepository.save(existingBuilding);

        return existingUser;
    }

    @Transactional
    public User deleteBuilding(String buildingId) {
        User existingUser = userRepository.findById(buildingId).orElse(null);
        buildingRepository.deleteById(buildingId);

        return existingUser;
    }



}
