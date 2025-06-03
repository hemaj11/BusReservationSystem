package model;

public class Ticket {
    private int id;
    private int busId;
    private String passengerName;
    private int seatsBooked;

    public Ticket() {}

    public Ticket(int busId, String passengerName, int seatsBooked) {
        this.busId = busId;
        this.passengerName = passengerName;
        this.seatsBooked = seatsBooked;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public int getSeatsBooked() { return seatsBooked; }
    public void setSeatsBooked(int seatsBooked) { this.seatsBooked = seatsBooked; }
}