import java.sql.*;
import java.util.List;
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class Faculty contains methods for retrieving and updating data from the faculty table of the database.
 * @author kshit
 */
public class Faculty 
{
    private static Connection connection;
    private static PreparedStatement addNewFaculty;
    private static PreparedStatement getAllFaculty;
    private static ResultSet resultSet;
    
    /**
     * Adds a new Faculty with a given name to the Faculty table.
     * @param name the faculty name
     * @return 1 if updated executed, 0 otherwise
     * @throws SQLException
     */
    public static int addFaculty(String name)
    {
        try
        {
            connection = DBConnection.getConnection();
            addNewFaculty = connection.prepareStatement("INSERT INTO FACULTY (NAME) VALUES (?)");
            addNewFaculty.setString(1, name);
            return addNewFaculty.executeUpdate();
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Returns all faculty members as a List of Strings
     * @return all current faculty members
     * @throws SQLException
     */
    public static List<String> getAllFaculty()
    {
        try
        {
            connection = DBConnection.getConnection();
            getAllFaculty = connection.prepareStatement("SELECT * FROM FACULTY ORDER BY NAME");
            resultSet = getAllFaculty.executeQuery();
            ArrayList<String> facultyList = new ArrayList<>();
            while(resultSet.next())
                facultyList.add(resultSet.getString("NAME"));
            return facultyList;
        }
        
        catch(SQLException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }
}
