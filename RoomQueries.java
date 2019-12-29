import java.sql.*;
import java.util.List;
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class RoomQueries contains methods for retrieving information from the Rooms table of the Database
 * @author kshit
 */
public class RoomQueries 
{
    private static Connection connection;
    private static PreparedStatement addNewRoom;
    private static PreparedStatement dropRoom;
    private static PreparedStatement getAllRooms;
    private static PreparedStatement getPossibleRooms;
    private static ResultSet resultSet;
    
    /**
     * Adds a new room to the Rooms table
     * @param room the room to be added
     * @return 1 if updated successfully, 0 otherwise
     * @throws SQLException
     */
    public static int addRoom(RoomEntry room)
    {
        try
        {
            connection = DBConnection.getConnection();
            addNewRoom = connection.prepareStatement("INSERT INTO ROOMS (NAME, SEATS) VALUES (?,?)");
            addNewRoom.setString(1, room.getName());
            addNewRoom.setInt(2, room.getSeats());
            return addNewRoom.executeUpdate();
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Removes a room from the database Rooms table
     * @param room name of the room to be deleted
     * @return 1 if executed successfully, 0 otherwise
     * @throws SQLException
     */
    public static int dropRoom(String roomName)
    {
        try
        {
            connection = DBConnection.getConnection();
            dropRoom = connection.prepareStatement("DELETE FROM ROOMS WHERE NAME = ?");
            dropRoom.setString(1, roomName);
            return dropRoom.executeUpdate();
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return 0;
        }
    }
    /**
     * Returns all rooms in the database as a List of RoomEntries.
     * @return list of all rooms
     * @throws SQLException
     */
    public static List<RoomEntry> getAllRooms()
    {
       try
        {
            connection = DBConnection.getConnection();
            getAllRooms = connection.prepareStatement("SELECT NAME,SEATS FROM ROOMS ORDER BY NAME");
            resultSet = getAllRooms.executeQuery();
            ArrayList<RoomEntry> roomsList = new ArrayList<>();
            while(resultSet.next())
            {
                String name = resultSet.getString("NAME");
                int seats = resultSet.getInt("SEATS");
                roomsList.add(new RoomEntry(name,seats));
            }
            return roomsList;
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }
    
    /**
     * Returns all possible rooms with at least a given number of seats.
     * @param seats the minimum number of seats
     * @return list of possible rooms
     * @throws SQLException
     */
    public static List<RoomEntry> getAllPossibleRooms(int seats)
    {
        try
        {
            connection = DBConnection.getConnection();
            getPossibleRooms = connection.prepareStatement("SELECT NAME,SEATS FROM ROOMS WHERE SEATS >= ? ORDER BY SEATS ASC");
            getPossibleRooms.setInt(1, seats);
            resultSet = getPossibleRooms.executeQuery();
            ArrayList<RoomEntry> roomsList = new ArrayList<>();
            while(resultSet.next())
            {
                String name = resultSet.getString("NAME");
                int seat = resultSet.getInt("SEATS");
                roomsList.add(new RoomEntry(name,seat));
            }
            return roomsList;
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }
}
