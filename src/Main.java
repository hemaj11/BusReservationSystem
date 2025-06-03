import controller.AdminController;
import controller.UserController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			AdminController adminController = new AdminController();
			UserController userController = new UserController();

			while (true) {
			    System.out.println("==== Welcome to Bus Ticket Reservation System ====");
			    System.out.println("1. Admin");
			    System.out.println("2. User");
			    System.out.println("3. Exit");
			    System.out.print("Choose role: ");
			    int roleChoice = scanner.nextInt();
			    scanner.nextLine(); // consume newline

			    switch (roleChoice) {
			        case 1:
			            while (true) {
			                System.out.println("\n--- Admin Menu ---");
			                System.out.println("1. Add Bus");
			                System.out.println("2. Delete Bus");
			                System.out.println("3. Update Bus Details");
			                System.out.println("4. View All Buses");
			                System.out.println("5. Logout");
			                System.out.print("Choice: ");
			                int adminChoice = scanner.nextInt();
			                scanner.nextLine(); // consume newline

			                switch (adminChoice) {
			                    case 1 -> adminController.addBus();
			                    case 2 -> adminController.deleteBus();
			                    case 3 -> adminController.updateBusDetails();
			                    case 4 -> adminController.viewAllBuses();
			                    case 5 -> { System.out.println("🔒 Logged out from Admin."); break; }
			                    default -> System.out.println("❌ Invalid choice.");
			                }
			                if (adminChoice == 5) break;
			            }
			            break;

			        case 2:
			            while (true) {
			                System.out.println("\n--- User Menu ---");
			                System.out.println("1. View Buses");
			                System.out.println("2. Book Ticket");
			                System.out.println("3. Cancel Booking");
			                System.out.println("4. Back");
			                System.out.print("Choice: ");
			                int userChoice = scanner.nextInt();
			                scanner.nextLine(); // consume newline

			                switch (userChoice) {
			                    case 1 -> userController.viewAllBuses();
			                    case 2 -> userController.bookTicket(userChoice, null, userChoice);
			                    case 3 -> userController.cancelBooking(userChoice);  // now matches parameterless method
			                    case 4 -> { System.out.println("🔙 Returning to main menu."); break; }
			                    default -> System.out.println("❌ Invalid choice.");
			                }
			                if (userChoice == 4) break;
			            }
			            break;

			        case 3:
			            System.out.println("👋 Thank you for using the Bus Ticket Reservation System!");
			            return;

			        default:
			            System.out.println("❌ Invalid role selected.");
			    }
			}
		}
    }
}
