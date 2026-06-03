import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcConnectionExample {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:students.db";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT NOT NULL, age INTEGER NOT NULL)");
            statement.executeUpdate("INSERT OR IGNORE INTO students (id, name, age) VALUES (1, 'Asha', 21)");
            statement.executeUpdate("INSERT OR IGNORE INTO students (id, name, age) VALUES (2, 'Rahul', 22)");

            try (ResultSet resultSet = statement.executeQuery("SELECT id, name, age FROM students ORDER BY id")) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("id") + ", " + resultSet.getString("name") + ", " + resultSet.getInt("age"));
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
