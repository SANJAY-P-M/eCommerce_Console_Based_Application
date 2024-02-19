package doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static Connector instance;
    private Connection connection;

    private Connector() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "12345");
        } catch (SQLException e) {
            System.out.println("Cannot connect to the database. Server might be busy.");
            e.printStackTrace();
        }
    }

    public static Connector getInstance() {
        if (instance == null) {
            instance = new Connector();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection; 
    }
}
