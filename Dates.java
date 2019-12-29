import java.sql.*;
import java.util.List;
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class Dates contains methods for retrieving data from the Dates table of the database.
 * @author kshit
 */
public class Dates 
{
    private static Connection connection;
    private static PreparedStatement addNewDate;
    private static PreparedStatement getAllDates;
    private static ResultSet resultSet;
   
    /**
     * Adds a new date object to the Dates tables
     * @param date the date to be added
     * @return 1 if updated successfully, 0 otherwise
     * @throws SQLException
     */
    public static int addDate(Date date)
    {
        try
        {
            connection = DBConnection.getConnection();
            addNewDate = connection.prepareStatement("INSERT INTO DATES (DATE) VALUES (?)");
            addNewDate.setDate(1, date);
            return addNewDate.executeUpdate();
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return 0;
        }
    }
    
    
    /**
     * Returns all dates from the Dates table as a List.
     * @return all dates
     * @throws SQLException
     */
    public static List<Date> getAllDates()
    {
        try
        {
            connection = DBConnection.getConnection();
            getAllDates = connection.prepareStatement("SELECT * FROM DATES ORDER BY DATE");
            resultSet = getAllDates.executeQuery();
            ArrayList<Date> datesList = new ArrayList<>();
            while(resultSet.next())
                datesList.add(resultSet.getDate("DATE"));
            return datesList;
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }
}
