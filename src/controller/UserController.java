package controller;

import dao.BookingDAO;
import dao.BusDAO;
import model.Booking;
import model.Bus;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserController {
    private final BusDAO busDAO;
    private final BookingDAO bookingDAO;
    private final Scanner scanner;

    public UserController() {
        busDAO = new BusDAO();
        bookingDAO = new BookingDAO();
        scanner = new Scanner(System.in);
    }

    /**
     * Display all available buses.
     */
    public void viewAllBuses() {
        List<Bus> buses = busDAO.getAllBuses();
        if (buses.isEmpty()) {
            System.out.println("⚠ No buses available.");
        } else {
            System.out.println("---- Available Buses ----");
            for (Bus bus : buses) {
                System.out.println(
                    "Bus ID: " + bus.getId() +
                    ", Name: " + bus.getName() +
                    ", Source: " + bus.getSource() +
                    ", Destination: " + bus.getDestination() +
                    ", Seats: " + bus.getAvailableSeats() + "/" + bus.getTotalSeats() +
                    ", Departure: " + bus.getDepartureDate()
                );
            }
        }
    }

    /**
     * Book tickets for a bus.
     */
    public void bookTicket(int busId, String name, int seats) {
        try {
            System.out.print("Enter Bus ID to book a ticket: ");
            busId = scanner.nextInt();
            scanner.nextLine();

            Bus bus = busDAO.getBusById(busId);
            if (bus == null) {
                System.out.println("⚠ Bus not found.");
                return;
            }

            System.out.print("Enter your name: ");
            String passengerName = scanner.nextLine();

            System.out.print("Enter number of seats to book: ");
            seats = scanner.nextInt();
            scanner.nextLine();

            if (seats <= 0 || seats > bus.getAvailableSeats()) {
                System.out.println("⚠ Invalid number of seats. Only " + bus.getAvailableSeats() + " seats available.");
                return;
            }

            Booking booking = new Booking(busId, passengerName, seats, LocalDate.now());
            int bookingId = bookingDAO.addBooking(booking);
            if (bookingId > 0) {
                busDAO.updateAvailableSeats(busId, bus.getAvailableSeats() - seats);
                System.out.println("✅ Ticket booked successfully! Booking ID: " + bookingId);
            } else {
                System.out.println("❌ Failed to book ticket.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error during booking process: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Cancel an existing booking.
     */
    public void cancelBooking(int bookingId) {
        try {
            System.out.print("Enter Booking ID to cancel: ");
            bookingId = scanner.nextInt();
            scanner.nextLine();

            Booking booking = bookingDAO.getBookingById(bookingId);
            if (booking == null) {
                System.out.println("⚠ Booking not found.");
                return;
            }

            boolean cancelled = bookingDAO.cancelBooking(bookingId, booking.getBusId(), booking.getSeats());
            if (cancelled) {
                Bus bus = busDAO.getBusById(booking.getBusId());
                if (bus != null) {
                    busDAO.updateAvailableSeats(bus.getId(), bus.getAvailableSeats() + booking.getSeats());
                }
                System.out.println("✅ Booking cancelled successfully.");
            } else {
                System.out.println("❌ Failed to cancel booking.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error while cancelling booking: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }
}
