package com.example.spring_boot.controller;

import com.example.spring_boot.dto.Building.CreateBuildingDTO;
import com.example.spring_boot.dto.MessageResponseDTO;
import com.example.spring_boot.dto.MessageWithDataResponseDTO;
import com.example.spring_boot.model.Building;
import com.example.spring_boot.model.User;
import com.example.spring_boot.service.BuildingService;
import com.example.spring_boot.service.LogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/building")
public class BuildingController {
    private final BuildingService buildingService;
    private final LogService logService;

    public BuildingController(BuildingService buildingService, LogService logService) {
        this.buildingService = buildingService;
        this.logService = logService;
    }

    @GetMapping("/all")
    public ResponseEntity<MessageWithDataResponseDTO> getBuildings() {
        List<Building> allBuildings = buildingService.getBuildings();
        return ResponseEntity.ok(new MessageWithDataResponseDTO("Buildings retrieved successfully", allBuildings));
    }

    @GetMapping("/{buildingId}")
    public ResponseEntity<MessageWithDataResponseDTO> getBuildingById(@Valid String buildingId) {
        Building building = buildingService.getBuildingById(buildingId);
        return ResponseEntity.ok(new MessageWithDataResponseDTO("Building retrieved successfully", building));
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponseDTO> createBuilding(@Valid CreateBuildingDTO building) {
        User existingUser = buildingService.createBuilding(building);
        logService.CreateBuilddingByAdminAction(existingUser);
        return ResponseEntity.ok(new MessageResponseDTO("Building created successfully!"));
    }

    @PutMapping("/update/{buildingId}")
    public ResponseEntity<MessageResponseDTO> updateBuilding(@Valid @PathVariable String buildingId, @Valid CreateBuildingDTO building) {
        User existingUser = buildingService.updateBuilding(buildingId, building);
        logService.UpdateBuildingByAdminAction(existingUser);
        return ResponseEntity.ok(new MessageResponseDTO("Building updated successfully!"));
    }

    @DeleteMapping("/delete/{buildingId}")
    public ResponseEntity<MessageResponseDTO> deleteBuilding(@Valid @PathVariable String buildingId) {
        User existingUser = buildingService.deleteBuilding(buildingId);
        logService.DeleteBuildingByAdminAction(existingUser);
        return ResponseEntity.ok(new MessageResponseDTO("Building deleted successfully!"));
    }
}
