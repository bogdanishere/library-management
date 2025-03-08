package com.example.spring_boot.dto.Building;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBuildingDTO {
    private String name;
    private String adminId;
}
