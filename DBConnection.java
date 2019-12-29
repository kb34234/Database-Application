import java.sql.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class to establish connection with the Database
 * @author kshit
 */
public class DBConnection
{
    private static Connection connection;
    private static final String user = "java";
    private static final String password = "java";
    private static final String address = "jdbc:derby://localhost:1527/RoomSchedulerDBKshitijkzb5629";
    
    /**
     * Establishes and fetches connection with the database.
     * @return the database connection
     * @throws SQLException
     */
    public static Connection getConnection()
    {
        try
        {
            connection = DriverManager.getConnection(address, user, password);
        }
        catch(SQLException exception)
        {
            exception.printStackTrace();
            System.exit(0);
        }
        
        return connection;
    }
}