import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcTransactionExample {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:bank.db";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS accounts (id INTEGER PRIMARY KEY, owner TEXT NOT NULL, balance REAL NOT NULL)");
            statement.executeUpdate("INSERT OR IGNORE INTO accounts (id, owner, balance) VALUES (1, 'Alice', 1000.0)");
            statement.executeUpdate("INSERT OR IGNORE INTO accounts (id, owner, balance) VALUES (2, 'Bob', 500.0)");

            transfer(connection, 1, 2, 100.0);

            try (ResultSet resultSet = statement.executeQuery("SELECT id, owner, balance FROM accounts ORDER BY id")) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("owner") + ": " + resultSet.getDouble("balance"));
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    static void transfer(Connection connection, int fromAccountId, int toAccountId, double amount) throws Exception {
        connection.setAutoCommit(false);

        try (PreparedStatement debitStatement = connection.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE id = ?");
             PreparedStatement creditStatement = connection.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE id = ?")) {

            debitStatement.setDouble(1, amount);
            debitStatement.setInt(2, fromAccountId);
            creditStatement.setDouble(1, amount);
            creditStatement.setInt(2, toAccountId);

            debitStatement.executeUpdate();
            creditStatement.executeUpdate();

            connection.commit();
        } catch (Exception exception) {
            connection.rollback();
            throw exception;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
