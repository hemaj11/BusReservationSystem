package controller;

import dao.BookingDAO;
import dao.BusDAO;
import model.Bus;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    private final BusDAO busDAO;
    private final BookingDAO bookingDAO;
    private final Scanner scanner;

    public AdminController() {
        busDAO = new BusDAO();          // wraps DBConnection exceptions internally
        bookingDAO = new BookingDAO();  // wraps DBConnection exceptions internally
        scanner = new Scanner(System.in);
    }

    /** 1) Add a new bus */
    public void addBus() {
        System.out.print("Enter Bus Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Source: ");
        String source = scanner.nextLine();

        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter Total Seats: ");
        int totalSeats = scanner.nextInt();

        System.out.print("Enter Available Seats: ");
        int availableSeats = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Departure Date (YYYY-MM-DD): ");
        LocalDate departureDate = LocalDate.parse(scanner.nextLine());

        Bus bus = new Bus();
        bus.setName(name);
        bus.setSource(source);
        bus.setDestination(destination);
        bus.setTotalSeats(totalSeats);
        bus.setAvailableSeats(availableSeats);
        bus.setDepartureDate(departureDate);

        try {
            boolean added = busDAO.addBus(bus);
            System.out.println(added ? "✅ Bus added successfully." : "❌ Failed to add bus.");
        } catch (SQLException e) {
            System.out.println("❌ Error adding bus: " + e.getMessage());
        }
    }

    /** 2) Delete a bus (and its bookings) */
    public void deleteBus() {
        System.out.print("Enter Bus ID to delete: ");
        int busId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try {
            // First delete associated bookings
            bookingDAO.deleteBookingsByBusId(busId);

            // Then delete the bus
            boolean deleted = busDAO.deleteBus(busId);
            System.out.println(deleted ? "✅ Bus deleted successfully." : "❌ Bus not found or could not be deleted.");
        } catch (SQLException e) {
            System.out.println("❌ Error deleting bus: " + e.getMessage());
        }
    }

    /** 3) Update details of an existing bus */
    public void updateBusDetails() {
        System.out.print("Enter Bus ID to update: ");
        int busId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter new Bus Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new Source: ");
        String source = scanner.nextLine();

        System.out.print("Enter new Destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter new Total Seats: ");
        int totalSeats = scanner.nextInt();

        System.out.print("Enter new Available Seats: ");
        int availableSeats = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter new Departure Date (YYYY-MM-DD): ");
        LocalDate departureDate = LocalDate.parse(scanner.nextLine());

        Bus bus = new Bus();
        bus.setId(busId);
        bus.setName(name);
        bus.setSource(source);
        bus.setDestination(destination);
        bus.setTotalSeats(totalSeats);
        bus.setAvailableSeats(availableSeats);
        bus.setDepartureDate(departureDate);

        try {
            boolean updated = busDAO.updateBus(bus);
            System.out.println(updated ? "✅ Bus updated successfully." : "❌ Bus not found or update failed.");
        } catch (SQLException e) {
            System.out.println("❌ Error updating bus: " + e.getMessage());
        }
    }

    /** 4) View all buses */
    public void viewAllBuses() {
        List<Bus> buses = busDAO.getAllBuses();  // does not throw SQLException
        if (buses.isEmpty()) {
            System.out.println("⚠ No buses available.");
        } else {
            System.out.println("---- Available Buses ----");
            for (Bus bus : buses) {
                System.out.println(bus);
            }
        }
    }
}
