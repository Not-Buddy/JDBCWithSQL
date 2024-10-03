import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableAndOperations {
    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Buddy"; // Change to your database
    private static final String USER = "root"; // Database username
    private static final String PASS = "password"; // Database password

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            // SQL statement to create a new table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Employees " +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                    " name VARCHAR(255), " +
                    " position VARCHAR(255), " +
                    " salary DECIMAL(10, 2))";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table created successfully.");

            // SQL statement to insert sample data (optional)
            String insertSQL = "INSERT INTO Employees (name, position, salary) VALUES " +
                    "('Alice', 'Manager', 75000), " +
                    "('Bob', 'Developer', 60000), " +
                    "('Charlie', 'Designer', 55000)";
            statement.executeUpdate(insertSQL);

            // SQL statement to retrieve data
            String selectSQL = "SELECT * FROM Employees";
            ResultSet resultSet = statement.executeQuery(selectSQL);

            // Accessing and printing the elements one by one
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                double salary = resultSet.getDouble("salary");

                System.out.println("ID: " + id + ", Name: " + name + ", Position: " + position + ", Salary: " + salary);
            }
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

