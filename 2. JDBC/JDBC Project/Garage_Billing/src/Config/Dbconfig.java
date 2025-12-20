package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconfig {
   private static final String URL="jdbc:mysql://127.0.0.1:3306/garage";
   private static final String USERNAME= "root";
   private static final String PASSWORD="Atharva@860087";

   public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(URL, USERNAME, PASSWORD);
   }
}
