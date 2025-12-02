package com.test;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose storage type:");
        System.out.println("1) MySQL database");
        System.out.println("2) In-memory repository");
        System.out.print("Enter choice (1 or 2): ");

        int choice = scanner.nextInt();
        boolean useMySQL = (choice == 1);
        boolean useInMemory = (choice == 2);

        App app = new App();
        app.run(useMySQL);
        app.run(useInMemory);
    }
}