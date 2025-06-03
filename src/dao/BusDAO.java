package dao;

import model.Bus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BusDAO {
    private final Connection connection;

    public BusDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to DB", e);
        }
    }

    public boolean addBus(Bus bus) throws SQLException {
        String sql = "INSERT INTO buses (name, source, destination, total_seats, available_seats, departure_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bus.getName());                // column: name
            stmt.setString(2, bus.getSource());              // column: source
            stmt.setString(3, bus.getDestination());         // column: destination
            stmt.setInt(4, bus.getTotalSeats());             // column: total_seats
            stmt.setInt(5, bus.getAvailableSeats());         // column: available_seats
            stmt.setDate(6, Date.valueOf(bus.getDepartureDate()));
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteBus(int busId) throws SQLException {
        String sql = "DELETE FROM buses WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, busId);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean updateBus(Bus bus) throws SQLException {
        String sql = "UPDATE buses SET name = ?, source = ?, destination = ?, total_seats = ?, available_seats = ?, departure_date = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bus.getName());
            stmt.setString(2, bus.getSource());
            stmt.setString(3, bus.getDestination());
            stmt.setInt(4, bus.getTotalSeats());
            stmt.setInt(5, bus.getAvailableSeats());
            stmt.setDate(6, Date.valueOf(bus.getDepartureDate()));
            stmt.setInt(7, bus.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT * FROM buses";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             while (rs.next()) {
                 Bus bus = new Bus();
                 bus.setId(rs.getInt("id"));
                 bus.setName(rs.getString("name"));                       // was bus_name
                 bus.setSource(rs.getString("source"));
                 bus.setDestination(rs.getString("destination"));
                 bus.setTotalSeats(rs.getInt("total_seats"));
                 bus.setAvailableSeats(rs.getInt("available_seats"));
                 bus.setDepartureDate(rs.getDate("departure_date").toLocalDate());
                 buses.add(bus);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buses;
    }

    public Bus getBusById(int busId) {
        String sql = "SELECT * FROM buses WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, busId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Bus bus = new Bus();
                    bus.setId(rs.getInt("id"));
                    bus.setName(rs.getString("name"));
                    bus.setSource(rs.getString("source"));
                    bus.setDestination(rs.getString("destination"));
                    bus.setTotalSeats(rs.getInt("total_seats"));
                    bus.setAvailableSeats(rs.getInt("available_seats"));
                    bus.setDepartureDate(rs.getDate("departure_date").toLocalDate());
                    return bus;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAvailableSeats(int busId, int newAvailable) {
        String sql = "UPDATE buses SET available_seats = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, newAvailable);
            stmt.setInt(2, busId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public void incrementSeats(int busId, int seats) throws SQLException {
        String query = "UPDATE buses SET available_seats = available_seats + ? WHERE id = ?";
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, seats);
            statement.setInt(2, busId);
            statement.executeUpdate();
        }
    }
}
