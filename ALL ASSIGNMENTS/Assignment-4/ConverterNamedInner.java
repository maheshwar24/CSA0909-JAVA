package assignment.task05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConverterNamedInner extends JFrame {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target = new JComboBox<>(new String[]{"INR", "USD", "EUR", "GBP"});
    private final JLabel result = new JLabel("Result: ");
    public ConverterNamedInner() {
        super("Converter – Named Inner Class"); setLayout(new FlowLayout());
        JButton button = new JButton("Convert"); add(amount); add(target); add(button); add(result);
        ConvertHandler handler = new ConvertHandler();
        button.addActionListener(handler); amount.addActionListener(handler);
        addWindowListener(new WindowAdapter() { @Override public void windowClosing(WindowEvent e) { dispose(); }});
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); pack(); setLocationRelativeTo(null);
    }
    private class ConvertHandler implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
            try {
                double value = ConverterSupport.parseAmount(amount, ConverterNamedInner.this);
                String cur = (String) target.getSelectedItem();
                String text = String.format("%.2f INR = %.2f %s", value, value * ConverterSupport.rate(cur), cur);
                result.setText(text); JOptionPane.showMessageDialog(ConverterNamedInner.this, text);
            } catch (NumberFormatException ex) { result.setText("Invalid amount"); }
        }
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new ConverterNamedInner().setVisible(true)); }
}
