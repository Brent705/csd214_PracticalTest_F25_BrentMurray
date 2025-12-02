package com.test.services;

import com.test.entities.VehicleEntity;

import java.util.List;
import java.util.Optional;

public interface VehicleService<T extends VehicleEntity> {
    Optional<T> findById(Long id);
    List<T> findAll();
    T save(T entity);
    void deleteById(Long id);
}