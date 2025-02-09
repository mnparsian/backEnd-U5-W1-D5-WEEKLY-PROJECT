package com.example.GestionePrenotazioni.service;

import com.example.GestionePrenotazioni.model.Building;
import com.example.GestionePrenotazioni.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;

    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    public void addBuilding(Building building) {
        buildingRepository.save(building);
    }

    public Optional<Building> getBuildingById(Long id) {
        return buildingRepository.findById(id);
    }

    public void deleteBuilding(Long id) {
        buildingRepository.deleteById(id);
    }
}
