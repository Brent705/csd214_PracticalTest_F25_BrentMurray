package com.test.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class CarEntity extends VehicleEntity {
    @Column(name = "numDoors")
    private int numDoors;

    public CarEntity(int numDoors) {
        super();
        this.numDoors = numDoors;
    }

    public CarEntity() {
    }

    public int getNumDoors() {
        return numDoors;
    }

    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CarEntity car)) return false;
        return Objects.equals(getId(), car.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Car{" +
                "numDoors=" + numDoors + super.toString();
    }
}
