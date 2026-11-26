package assignment.task01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/** Application/service logic: validates and records a fee payment. */
public class FeeService {
    private final String url, user, password;

    public FeeService(String url, String user, String password) {
        this.url = url; this.user = user; this.password = password;
    }

    public void pay(String registrationNumber, String amountText)
            throws IllegalArgumentException, SQLException {
        if (registrationNumber == null || registrationNumber.isBlank())
            throw new IllegalArgumentException("Registration number is required.");
        final double amount;
        try { amount = Double.parseDouble(amountText); }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Enter a valid numeric amount.");
        }
        if (!Double.isFinite(amount) || amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than zero.");

        String sql = "INSERT INTO fees (reg_no, amount) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, registrationNumber.trim());
            statement.setDouble(2, amount);
            statement.executeUpdate();
        }
    }
}
