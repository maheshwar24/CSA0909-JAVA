package assignment.task05;

import javax.swing.*;
import java.awt.*;

final class ConverterSupport {
    private ConverterSupport() {}
    static double rate(String currency) {
        return switch (currency) {
            case "USD" -> 0.012; case "EUR" -> 0.011; case "GBP" -> 0.0095; default -> 1.0;
        };
    }
    static void showInvalid(Component parent) {
        JOptionPane.showMessageDialog(parent, "Please enter a valid amount",
                "Invalid amount", JOptionPane.ERROR_MESSAGE);
    }
    static double parseAmount(JTextField amount, Component parent) {
        try {
            double value = Double.parseDouble(amount.getText().trim());
            if (!Double.isFinite(value) || value < 0) throw new NumberFormatException();
            return value;
        } catch (NumberFormatException ex) {
            showInvalid(parent); throw ex;
        }
    }
}
