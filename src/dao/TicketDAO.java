package dao;

import model.Ticket;

//import java.util.*;
import java.sql.*;

public class TicketDAO {
    public void bookTicket(Ticket ticket) {
        String sql = "INSERT INTO tickets (bus_id, passenger_name, seats_booked) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getBusId());
            stmt.setString(2, ticket.getPassengerName());
            stmt.setInt(3, ticket.getSeatsBooked());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelTicket(int ticketId) {
        String sql = "DELETE FROM tickets WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ticketId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}