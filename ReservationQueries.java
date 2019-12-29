import java.sql.*;
import java.util.List;
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class ReservationQueries contains methods for retrieving and updating information from the Reservations table of the Database.
 * @author kshit
 */
public class ReservationQueries 
{
    private static Connection connection;
    private static PreparedStatement getReservationsByDate;
    private static PreparedStatement getReservationsByFaculty;
    private static PreparedStatement getRoomsReservedByDate;
    private static PreparedStatement addNewReservation;
    private static PreparedStatement deleteReservation;
    private static ResultSet resultSet;
    
    /**
     * Adds a new reservation entry to the Reservations table
     * @param entry the entry to be added
     * @return 1 if update is successful, 0 otherwise
     * @throws SQLException
     */
    public static int addReservationEntry(ReservationEntry entry)
    {
        try
        {
            connection = DBConnection.getConnection();
            addNewReservation = connection.prepareStatement("INSERT INTO RESERVATIONS (FACULTY, ROOM, DATE, SEATS, TIMESTAMP) VALUES (?, ?, ?, ?, ?)");
            addNewReservation.setString(1, entry.getFaculty());
            addNewReservation.setString(2, entry.getRoom());
            addNewReservation.setDate(3, entry.getDate());
            addNewReservation.setInt(4, entry.getSeats());
            addNewReservation.setTimestamp(5, entry.getTimeStamp());
            return addNewReservation.executeUpdate();
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Deletes an entry from the reservations table, given faculty name and date.
     * @param facultyName the name of faculty to be deleted
     * @param date the date of reservation
     * @return 1 if successfully deleted, 0 otherwise
     */
    public static int deleteReservation(String facultyName, Date date)
    {
        try
        {
            connection = DBConnection.getConnection();
            deleteReservation = connection.prepareStatement("DELETE FROM RESERVATIONS WHERE FACULTY = ? AND DATE = ?");
            deleteReservation.setString(1, facultyName);
            deleteReservation.setDate(2, date);
            return deleteReservation.executeUpdate();
        }
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Returns the list of all reservations on a particular date as a List of ReservationEntries.
     * @param date the date of reservation
     * @return the list of all reservations
     * @throws SQLException
     */
    public static List<ReservationEntry> getReservationsByDate(Date date)
    {
       try
        {
            connection = DBConnection.getConnection();
            getReservationsByDate = connection.prepareStatement("SELECT FACULTY, ROOM, DATE, SEATS, TIMESTAMP FROM RESERVATIONS WHERE DATE = ? ORDER BY TIMESTAMP ASC");
            getReservationsByDate.setDate(1, date);
            resultSet = getReservationsByDate.executeQuery();
            ArrayList<ReservationEntry> reservationsList = new ArrayList<>();
            while(resultSet.next())
            {
                String faculty = resultSet.getString("FACULTY");
                String room = resultSet.getString("ROOM");
                Date d = resultSet.getDate("DATE");
                int seats = resultSet.getInt("SEATS");
                Timestamp ts = resultSet.getTimestamp("TIMESTAMP");
                reservationsList.add(new ReservationEntry(faculty,room,d,seats,ts));
            }
            return reservationsList;
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }
    
    /**
     * Returns the list of reservations for a particular faculty as a List of ReservationEntry objects
     * @param facultyName the name of the faculty
     * @return a list of the faculty's reservations
     * @throws SQLException
     */
    public static List<ReservationEntry> getReservationsByFaculty(String facultyName)
    {
       try
        {
            connection = DBConnection.getConnection();
            getReservationsByFaculty = connection.prepareStatement("SELECT FACULTY, ROOM, DATE, SEATS, TIMESTAMP FROM RESERVATIONS WHERE FACULTY = ? ORDER BY DATE, TIMESTAMP");
            getReservationsByFaculty.setString(1, facultyName);
            resultSet = getReservationsByFaculty.executeQuery();
            ArrayList<ReservationEntry> reservationsList = new ArrayList<>();
            while(resultSet.next())
            {
                String faculty = resultSet.getString("FACULTY");
                String room = resultSet.getString("ROOM");
                Date d = resultSet.getDate("DATE");
                int seats = resultSet.getInt("SEATS");
                Timestamp ts = resultSet.getTimestamp("TIMESTAMP");
                reservationsList.add(new ReservationEntry(faculty,room,d,seats,ts));
            }
            return reservationsList;
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }
    
    /**
     * Returns the list of names of all rooms reserved on a given date as a List of Strings.
     * @param date the date of reservation
     * @return the list of rooms reserved
     * @throws SQLException
     */
    public static List<String> getRoomsReservedByDate(Date date)
    {
       try
        {
            connection = DBConnection.getConnection();
            getRoomsReservedByDate = connection.prepareStatement("SELECT ROOM FROM RESERVATIONS WHERE DATE = ? ORDER BY ROOM");
            getRoomsReservedByDate.setDate(1, date);
            resultSet = getRoomsReservedByDate.executeQuery();
            ArrayList<String> roomsList = new ArrayList<>();
            while(resultSet.next())
            {
                roomsList.add(resultSet.getString("ROOM"));
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
