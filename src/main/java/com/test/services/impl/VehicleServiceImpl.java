package com.test.services.impl;

import com.test.entities.CarEntity;
import com.test.entities.MotorcycleEntity;
import com.test.entities.VehicleEntity;
import com.test.repositories.VehicleRepository;
import com.test.services.VehicleService;

import java.util.List;
import java.util.Optional;

public class VehicleServiceImpl<T extends VehicleEntity> implements VehicleService<T> {
    private final VehicleRepository<T> repository;

    public VehicleServiceImpl(VehicleRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public VehicleEntity save(VehicleEntity entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    public CarEntity createCar(String make, String model, int year, int numDoors) {
        CarEntity car = new CarEntity();
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setNumDoors(numDoors);
        return (CarEntity) repository.save((T) car);
    }

    public MotorcycleEntity createMotorcycle(String make, String model, int year, boolean hasSideCar) {
        MotorcycleEntity moto = new MotorcycleEntity();
        moto.setMake(make);
        moto.setModel(model);
        moto.setYear(year);
        moto.setHasSideCar(hasSideCar);
        return (MotorcycleEntity) repository.save((T) moto);
    }

    public List<T> getAllVehicles() {
        return repository.findAll();
    }

    public void deleteVehicleById(Long id) {
        Optional<T> existing = repository.findById(id);
        if (existing.isEmpty()) {
            throw new RuntimeException("Vehicle with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
