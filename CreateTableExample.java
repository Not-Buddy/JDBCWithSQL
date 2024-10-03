import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableExample {
    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Buddy"; // Change to your database
    private static final String USER = "root"; // Change to your username
    private static final String PASS = "password"; // Change to your password

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            // SQL statement to create a new table
            String sql = "CREATE TABLE IF NOT EXISTS Employees " +
                         "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                         " name VARCHAR(255), " +
                         " position VARCHAR(255), " +
                         " salary DECIMAL(10, 2))";

            // Execute the SQL statement
            statement.executeUpdate(sql);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Clean up
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

