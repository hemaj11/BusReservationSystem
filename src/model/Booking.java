package model;

import java.time.LocalDate;

public class Booking {
    private int busId;
    private String passengerName;
    private int seats;
    private LocalDate bookingDate;

    public Booking(int busId, String passengerName, int seats, LocalDate bookingDate) {
        this.busId = busId;
        this.passengerName = passengerName;
        this.seats = seats;
        this.bookingDate = bookingDate;
    }

    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
}
