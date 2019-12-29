import java.sql.Date;
import java.sql.Timestamp;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class ReservationEntry represents a single entry in the Reservations table of the Database.
 * @author kshit
 */
public class ReservationEntry 
{
    private String faculty;
    private String room;
    private Date date;
    private int seats;
    private Timestamp timestamp;
    
    /**
     * Creates a new ReservationEntry with a faculty name, room name, date, number of seats and timestamp
     * @param faculty the faculty name
     * @param room the room name
     * @param date the reservation date
     * @param seats number of seats requested
     * @param ts the timestamp reservation was made at.
     */
    public ReservationEntry(String faculty, String room, Date date, int seats, Timestamp ts)
    {
        this.faculty = faculty;
        this.room = room;
        this.date = date;
        if(seats > 0)
            this.seats = seats;
        else
            throw new IllegalArgumentException("Invalid number of seats.");
        timestamp = ts;
    }
    
    /**
     * Returns the faculty name of the reservation.
     * @return the faculty name
     */
    public String getFaculty()
    {
        return faculty;
    }
    
    /**
     * Sets the faculty name to a given value.
     * @param newFaculty the new faculty name
     */
    public void setFaculty(String newFaculty)
    {
        faculty = newFaculty;
    }
    
    /**
     * Returns the room name of the reservation.
     * @return the room name
     */
    public String getRoom()
    {
        return room;
    }
    
    /**
     * Sets the room name to a given value
     * @param newRoom the new name of the room
     */
    public void setRoom(String newRoom)
    {
        room = newRoom;
    }
    
    /**
     * Returns the date of the reservation
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }
    
    /**
     * Sets the date of the reservation to a new value.
     * @param newDate the new date
     */
    public void setDate(Date newDate)
    {
        date = newDate;
    }
    
    /**
     * Returns the number of seats in the reservation.
     * @return the number of seats.
     */
    public int getSeats()
    {
        return seats;
    }
    
    /**
     * Sets the number of seats to a given value.
     * @param seats the new number of seats
     */
    public void setSeats(int seats)
    {
        if(seats > 0)
            this.seats = seats;
        else
            throw new IllegalArgumentException("Invalid number of seats.");
    }
    
    /**
     * Returns the timestamp of the reservation
     * @return the timestamp 
     */
    public Timestamp getTimeStamp()
    {
        return timestamp;
    }
    
    /**
     * Checks if two Reservation Entries are equal (have the same faculty, room, seats, date and timestamp values.
     * @param obj the other ReservationEntry
     * @return true if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        ReservationEntry other = (ReservationEntry)obj;
        return (this.faculty.equals(other.faculty)) && (this.room.equals(other.room)) && (this.date.equals(other.date)) && (this.seats == other.seats) && (this.timestamp.equals(other.timestamp));
    }
}
