package com.test.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class MotorcycleEntity extends VehicleEntity {
    @Column(name = "has_side_car")
    private boolean hasSideCar;

    public MotorcycleEntity(boolean hasSideCar) {
        super();
    }

    public MotorcycleEntity() {

    }

    public boolean getHasSideCar() {
        return hasSideCar;
    }

    public void setHasSideCar(boolean hasSideCar) {
        this.hasSideCar = hasSideCar;
    }
}
