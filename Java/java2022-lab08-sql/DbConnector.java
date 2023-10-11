package pl.edu.uj.sender;
import  java.sql.Connection;
import  java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static String URL="jdbc:mysql://localhost:3306/uj_sender";
    private static String USER="sender-app";
    private static String PASSWORD="#Pass123";
    private static Connection connection=null;
    public  static Connection connect(){

        try {
            connection=DriverManager.getConnection(URL,USER,PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
