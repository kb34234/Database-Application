import java.sql.Date;
import java.sql.Timestamp;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class WaitListEntry represents a single entry in the Waitlist table of the Database.
 * @author kshit
 */
public class WaitlistEntry 
{
    private String faculty;
    private Date date;
    private int seats;
    private Timestamp timestamp;
    
    /**
     * Creates a new WaitlistEntry with a given faculty name, date, number of seats and timestamp
     * @param faculty the faculty name
     * @param date the date of reservation
     * @param seats number of seats
     * @param ts timestamp reservation was requested
     */
    public WaitlistEntry(String faculty, Date date, int seats, Timestamp ts)
    {
        this.faculty = faculty;
        this.date = date;
        if(seats > 0)
            this.seats = seats;
        else
            throw new IllegalArgumentException("Invalid number of seats.");
        timestamp = ts;
    }
    
    /**
     * Returns the name of the faculty on the waitlist
     * @return name of faculty
     */
    public String getFaculty()
    {
        return faculty;
    }
    
    /**
     * Sets the name of the faculty to a given value
     * @param newFaculty the new faculty name
     */
    public void setFaculty(String newFaculty)
    {
        faculty = newFaculty;
    }
    
    /**
     * Returns the Date the reservation was requested for
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }
    
    /**
     * Changes the date of the waitlist entry to a new value
     * @param newDate the new date value
     */
    public void setDate(Date newDate)
    {
        date = newDate;
    }
    
    /**
     * Returns the number of seats requested in the Waitlist entry
     * @return the number of seats
     */
    public int getSeats()
    {
        return seats;
    }
    
    /**
     * Sets the number of seats to a given value
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
     * Returns the timestamp the reservation was requested on
     * @return the timestamp
     */
    public Timestamp getTimeStamp()
    {
        return timestamp;
    }
    
    /**
     * Checks if two Waitlist Entries are equal (have the same faculty, date, number of seats and timestamp).
     * @param obj the other entry
     * @return true if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        WaitlistEntry other = (WaitlistEntry)obj;
        return (this.faculty.equals(other.faculty)) && (this.date.equals(other.date)) && (this.seats == other.seats) && (this.timestamp.equals(other.timestamp));
    }
}
