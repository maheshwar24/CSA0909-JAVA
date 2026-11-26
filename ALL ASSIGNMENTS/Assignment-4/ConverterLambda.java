package assignment.task05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConverterLambda extends JFrame {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target = new JComboBox<>(new String[]{"INR", "USD", "EUR", "GBP"});
    private final JLabel result = new JLabel("Result: ");

    public ConverterLambda() {
        super("Currency Converter – Lambda");
        setLayout(new FlowLayout());
        JButton convertButton = new JButton("Convert");
        add(new JLabel("INR")); add(amount); add(target); add(convertButton); add(result);

        // TODO 1 completed: one lambda listener for button and Enter in amount field.
        ActionListener convertAction = e -> convert();
        convertButton.addActionListener(convertAction);
        amount.addActionListener(convertAction);

        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack(); setLocationRelativeTo(null);
    }

    private void convert() {
        // TODO 2 completed: parse safely and show invalid-amount dialog on failure.
        try {
            double inr = ConverterSupport.parseAmount(amount, this);
            String currency = (String) target.getSelectedItem();
            double converted = inr * ConverterSupport.rate(currency);
            String message = String.format("%.2f INR = %.2f %s", inr, converted, currency);
            result.setText(message);
            JOptionPane.showMessageDialog(this, message, "Conversion", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ignored) {
            result.setText("Invalid amount");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConverterLambda().setVisible(true));
    }
}
