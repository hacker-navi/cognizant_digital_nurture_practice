import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class JdbcStudentDaoExample {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:students.db";

        try (Connection connection = DriverManager.getConnection(url)) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT NOT NULL, age INTEGER NOT NULL)");
            }

            StudentDAO studentDAO = new StudentDAO(connection);
            studentDAO.insertStudent(new Student(3, "Meera", 20));
            studentDAO.updateStudentName(3, "Meera Sharma");

            System.out.println("Insert and update completed.");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    record Student(int id, String name, int age) {
    }

    static class StudentDAO {
        private final Connection connection;

        StudentDAO(Connection connection) {
            this.connection = connection;
        }

        void insertStudent(Student student) throws Exception {
            String sql = "INSERT OR REPLACE INTO students (id, name, age) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, student.id());
                preparedStatement.setString(2, student.name());
                preparedStatement.setInt(3, student.age());
                preparedStatement.executeUpdate();
            }
        }

        void updateStudentName(int id, String name) throws Exception {
            String sql = "UPDATE students SET name = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }
        }
    }
}
