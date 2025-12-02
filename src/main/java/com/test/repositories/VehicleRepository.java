package com.test.repositories;

import com.test.entities.VehicleEntity;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository<T extends VehicleEntity> {
    Optional<T> findById(Long id);
    List<T> findAll();
    T save(T entity);
    void deleteById(Long id);
}