package dao;

import model.Booking;
//import util.DBConnection;

import java.sql.*;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;

public class BookingDAO {
    private final Connection connection;

    public BookingDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to DB in BookingDAO", e);
        }
    }

    /**
     * Adds a new booking and returns the generated booking ID.
     */
    public int addBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO bookings (bus_id, passenger_name, seats, booking_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, booking.getBusId());
            stmt.setString(2, booking.getPassengerName());
            stmt.setInt(3, booking.getSeats());
            stmt.setDate(4, Date.valueOf(booking.getBookingDate()));

            int rows = stmt.executeUpdate();
            if (rows == 0) return -1;
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
            return -1;
        }
    }

    /**
     * Retrieves a booking by its ID.
     */
    public Booking getBookingById(int bookingId) throws SQLException {
        String sql = "SELECT * FROM bookings WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Booking(
                        rs.getInt("bus_id"),
                        rs.getString("passenger_name"),
                        rs.getInt("seats"),
                        rs.getDate("booking_date").toLocalDate()
                    );
                }
            }
        }
        return null;
    }

    /**
     * Cancels a booking by its ID and restores seats to the bus.
     */
    public boolean cancelBooking(int bookingId, int busId, int seats) throws SQLException {
        String deleteSql = "DELETE FROM bookings WHERE id = ?";
        String updateSql = "UPDATE buses SET available_seats = available_seats + ? WHERE id = ?";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
                deleteStmt.setInt(1, bookingId);
                int rows = deleteStmt.executeUpdate();
                if (rows == 0) {
                    connection.rollback();
                    return false;
                }
            }

            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                updateStmt.setInt(1, seats);
                updateStmt.setInt(2, busId);
                updateStmt.executeUpdate();
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
    

    
    public boolean deleteBookingsByBusId(int busId) throws SQLException {
        String sql = "DELETE FROM bookings WHERE bus_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, busId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }
}

