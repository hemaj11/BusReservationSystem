package model;

import java.time.LocalDate;

public class Bus {
    private int id;
    private String name;
    private String source;        // renamed back to “source”
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private LocalDate departureDate;

    /** Default constructor */
    public Bus() {}

    /** Full constructor */
    public Bus(int id, String name, String source, String destination, int totalSeats, int availableSeats, LocalDate departureDate) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.departureDate = departureDate;
    }

    // ——— Getters & Setters ———

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "Bus ID: " + id +
               ", Name: " + name +
               ", Source: " + source +
               ", Destination: " + destination +
               ", Seats: " + availableSeats + "/" + totalSeats +
               ", Departure: " + departureDate;
    }
}
