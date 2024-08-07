package com.preciousmotloung.hotel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL ="jdbc:mysql://localhost:3306/hotel_reservations";
    private static final String USER = "root";
    private static final String PASSWORD = "ccBB01@#";

    static
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch (ClassNotFoundException e)
        {
            throw new RuntimeException("MySQL Driver not found");
        }
    }

    public static Connection getConnection()throws SQLException
    {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

    public static void checkDatabaseConnection()
    {
        try(Connection connection = getConnection())
        {
            if(connection != null && !connection.isClosed()){
                System.out.println("Database connection is successful.");
            }else {
                System.out.println("Failed to connect to the database");
            }
        }catch (SQLException e){
            System.out.println("Failed to connect to the database: "+e.getMessage());
        }
    }

}
