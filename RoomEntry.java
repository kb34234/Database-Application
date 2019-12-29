/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class RoomEntry represents a single entry in the Rooms table of the Database.
 * @author kshit
 */
public class RoomEntry 
{
    private String name;
    private int seats;
    
    /**
     * Creates a new RoomEntry with a given name and number of seats.
     * @param name the room name
     * @param seats number of seats in the room
     */
    public RoomEntry(String name, int seats)
    {
        this.name = name;
        if(seats > 0)
            this.seats = seats;
        else
            throw new IllegalArgumentException("Invalid number of seats.");
    }
    
    /**
     * Returns the name of the room
     * @return the room name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets the name of the room to a given value
     * @param name the new name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Returns the number of seats in the room
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
     * Checks if two Room Entries are equal (have the same name and number of seats).
     * @param obj the other RoomEntry
     * @return true if the entries are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        RoomEntry other = (RoomEntry)obj;
        return (this.name.equals(other.name)) && (this.seats == other.seats);
    }
}
