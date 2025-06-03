package view;

import controller.UserController;
import java.util.Scanner;

public class UserView {
    private final UserController userController = new UserController();
    private final Scanner scanner = new Scanner(System.in);

    public void userMenu() {
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Buses");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Back");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    userController.viewAllBuses();
                    break;
                case 2:
                    bookTicket();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("❌ Invalid choice, try again.");
            }
        }
    }

    private void bookTicket() {
        System.out.print("Enter Bus ID to book: ");
        int busId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Passenger Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter number of seats: ");
        int seats = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        userController.bookTicket(busId, name, seats);
    }

    private void cancelBooking() {
        System.out.print("Enter Booking ID to cancel: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

//        userController.cancelTicket(bookingId);
        userController.cancelBooking(bookingId);

    }
}
