package view;

import controller.AdminController;

import java.util.Scanner;

public class AdminView {
    private final AdminController adminController = new AdminController();
    private final Scanner scanner = new Scanner(System.in);

    public void adminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Bus");
            System.out.println("2. Delete Bus");
            System.out.println("3. Update Bus Details");
            System.out.println("4. View All Buses");
            System.out.println("5. Logout");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1 -> adminController.addBus();
                case 2 -> adminController.deleteBus();
                case 3 -> adminController.updateBusDetails(); // now defined
                case 4 -> adminController.viewAllBuses();
                case 5 -> { System.out.println("✅ Logged out."); return; }
                default -> System.out.println("❌ Invalid choice.");
            }
        }
    }
}
