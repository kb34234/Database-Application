import java.sql.*;
import java.util.List;
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class WaitListQueries contains methods for retrieving and updating information in the Waitlist table of the Database.
 * @author kshit
 */
public class WaitlistQueries 
{
    private static Connection connection;
    private static PreparedStatement getAllWaitlist;
    private static PreparedStatement getWaitlistByDate;
    private static PreparedStatement getWaitlistByFaculty;
    private static PreparedStatement addNewWaitlistEntry;
    private static PreparedStatement deleteWaitlistEntry;
    private static ResultSet resultSet;
    
    /**
     * Adds a new entry to the waitlist.
     * @param entry the entry to be added
     * @return 1 if successfully updated, 0 otherwise
     * @throws SQLException
     */
    public static int addWaitlistEntry(WaitlistEntry entry)
    {
        try
        {
            connection = DBConnection.getConnection();
            addNewWaitlistEntry = connection.prepareStatement("INSERT INTO WAITLIST (FACULTY, DATE, SEATS, TIMESTAMP) VALUES (?, ?, ?, ?)");
            addNewWaitlistEntry.setString(1, entry.getFaculty());
            addNewWaitlistEntry.setDate(2, entry.getDate());
            addNewWaitlistEntry.setInt(3, entry.getSeats());
            addNewWaitlistEntry.setTimestamp(4, entry.getTimeStamp());
            return addNewWaitlistEntry.executeUpdate();
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Deletes an entry from the waitlist table, given faculty name and date.
     * @param facultyName the name of faculty to be deleted
     * @param date the date of reservation
     * @return 1 if successfully deleted, 0 otherwise
     */
    public static int deleteWaitlistEntry(String facultyName, Date date)
    {
        try
        {
            connection = DBConnection.getConnection();
            deleteWaitlistEntry = connection.prepareStatement("DELETE FROM WAITLIST WHERE FACULTY = ? AND DATE = ?");
            deleteWaitlistEntry.setString(1, facultyName);
            deleteWaitlistEntry.setDate(2, date);
            return deleteWaitlistEntry.executeUpdate();
        }
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Returns all entries in the waitlist as a List of WaitlistEntries.
     * @return all entries in the waitlist
     * @throws SQLException
     */
    public static List<WaitlistEntry> getAllWaitlist()
    {
       try
        {
            connection = DBConnection.getConnection();
            getAllWaitlist = connection.prepareStatement("SELECT FACULTY, DATE, SEATS, TIMESTAMP FROM WAITLIST ORDER BY DATE, TIMESTAMP");
            resultSet = getAllWaitlist.executeQuery();
            ArrayList<WaitlistEntry> waitlist = new ArrayList<>();
            while(resultSet.next())
            {
                String faculty = resultSet.getString("FACULTY");
                Date date = resultSet.getDate("DATE");
                int seats = resultSet.getInt("SEATS");
                Timestamp ts = resultSet.getTimestamp("TIMESTAMP");
                waitlist.add(new WaitlistEntry(faculty,date,seats,ts));
            }
            return waitlist;
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }
    
    /**
     * Returns a list of the Waitlist entries for a given faculty as a list of WaitlistEntry objects.
     * @param facultyName the name of the faculty
     * @return a list of the faculty's entries
     * @throws SQLException
     */
    public static List<WaitlistEntry> getWaitlistByFaculty(String facultyName)
    {
       try
        {
            connection = DBConnection.getConnection();
            getWaitlistByFaculty = connection.prepareStatement("SELECT FACULTY, DATE, SEATS, TIMESTAMP FROM WAITLIST WHERE FACULTY = ? ORDER BY DATE, TIMESTAMP");
            getWaitlistByFaculty.setString(1, facultyName);
            resultSet = getWaitlistByFaculty.executeQuery();
            ArrayList<WaitlistEntry> waitlist = new ArrayList<>();
            while(resultSet.next())
            {
                String faculty = resultSet.getString("FACULTY");
                Date d = resultSet.getDate("DATE");
                int seats = resultSet.getInt("SEATS");
                Timestamp ts = resultSet.getTimestamp("TIMESTAMP");
                waitlist.add(new WaitlistEntry(faculty,d,seats,ts));
            }
            return waitlist;
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }
    
    /**
     * Returns all waitlist entries on a given date as a List of WaitlistEntries.
     * @param date the date
     * @return the list of entries on a date
     * @throws SQLException
     */
    public static List<WaitlistEntry> getWaitlistByDate(Date date)
    {
       try
        {
            connection = DBConnection.getConnection();
            getWaitlistByDate = connection.prepareStatement("SELECT FACULTY, DATE, SEATS, TIMESTAMP FROM WAITLIST WHERE DATE = ? ORDER BY TIMESTAMP");
            getWaitlistByDate.setDate(1, date);
            resultSet = getWaitlistByDate.executeQuery();
            ArrayList<WaitlistEntry> waitlist = new ArrayList<>();
            while(resultSet.next())
            {
                String faculty = resultSet.getString("FACULTY");
                Date d = resultSet.getDate("DATE");
                int seats = resultSet.getInt("SEATS");
                Timestamp ts = resultSet.getTimestamp("TIMESTAMP");
                waitlist.add(new WaitlistEntry(faculty,d,seats,ts));
            }
            return waitlist;
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }
}

