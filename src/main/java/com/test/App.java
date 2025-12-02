package com.test;

import com.test.entities.VehicleEntity;
import com.test.repositories.VehicleRepository;
import com.test.repositories.impl.MySQLVehicleRepository;
import com.test.repositories.impl.InMemoryVehicleRepository;
import com.test.services.impl.VehicleServiceImpl;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

    private EntityManagerFactory emf;

    public void run(boolean useMySQL) {

        VehicleRepository<VehicleEntity> repo;
        VehicleServiceImpl<VehicleEntity> service;

        if (useMySQL) {
            System.out.println("Using MySQL repository...");
            emf = Persistence.createEntityManagerFactory("vehicle-pu");
            repo = new MySQLVehicleRepository<>(emf, VehicleEntity.class);
        } else {
            System.out.println("Using InMemory repository...");
            repo = new InMemoryVehicleRepository<>();
        }

        service = new VehicleServiceImpl<>(repo);

        mainMenu(service);
    }

    private void mainMenu(VehicleServiceImpl<VehicleEntity> service) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1) Create Car");
            System.out.println("2) Create Motorcycle");
            System.out.println("3) List All Vehicles");
            System.out.println("4) Find Vehicle By ID");
            System.out.println("5) Delete Vehicle By ID");
            System.out.println("0) Exit");
            System.out.print("Select: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> createCar(scanner, service);
                case 2 -> createMotorcycle(scanner, service);
                case 3 -> listAll(service);
                case 4 -> findById(scanner, service);
                case 5 -> deleteById(scanner, service);
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void createCar(Scanner scanner, VehicleServiceImpl<VehicleEntity> service) {
        System.out.println("\n--- Create Car ---");

        System.out.print("Make: ");
        String make = scanner.next();

        System.out.print("Model: ");
        String model = scanner.next();

        System.out.print("Year: ");
        int year = scanner.nextInt();

        System.out.print("Number of doors: ");
        int numDoors = scanner.nextInt();

        var car = service.createCar(make, model, year, numDoors);
        System.out.println("Created: " + car);
    }

    private void createMotorcycle(Scanner scanner, VehicleServiceImpl<VehicleEntity> service) {
        System.out.println("\n--- Create Motorcycle ---");

        System.out.print("Make: ");
        String make = scanner.next();

        System.out.print("Model: ");
        String model = scanner.next();

        System.out.print("Year: ");
        int year = scanner.nextInt();

        System.out.print("Has sidecar? (true/false): ");
        boolean hasSideCar = scanner.nextBoolean();

        var moto = service.createMotorcycle(make, model, year, hasSideCar);
        System.out.println("Created: " + moto);
    }

    private void listAll(VehicleServiceImpl<VehicleEntity> service) {
        System.out.println("\n--- All Vehicles ---");

        List<VehicleEntity> list = service.getAllVehicles();
        if (list.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        list.forEach(System.out::println);
    }

    private void findById(Scanner scanner, VehicleServiceImpl<VehicleEntity> service) {
        System.out.print("\nEnter ID to find: ");
        long id = scanner.nextLong();

        Optional<VehicleEntity> result = service.findById(id);

        if (result.isPresent()) {
            System.out.println("Found: " + result.get());
        } else {
            System.out.println("No vehicle found with ID " + id);
        }
    }

    private void deleteById(Scanner scanner, VehicleServiceImpl<VehicleEntity> service) {
        System.out.print("\nEnter ID to delete: ");
        long id = scanner.nextLong();

        try {
            service.deleteVehicleById(id);
            System.out.println("Vehicle deleted.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}