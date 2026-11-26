package assignment.task05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConverterFrameListener extends JFrame implements ActionListener {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target = new JComboBox<>(new String[]{"INR", "USD", "EUR", "GBP"});
    private final JLabel result = new JLabel("Result: ");
    private final JButton button = new JButton("Convert");
    public ConverterFrameListener() {
        super("Converter – Frame Listener"); setLayout(new FlowLayout());
        add(amount); add(target); add(button); add(result);
        button.addActionListener(this); amount.addActionListener(this);
        addWindowListener(new WindowAdapter() { @Override public void windowClosing(WindowEvent e) { dispose(); }});
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); pack(); setLocationRelativeTo(null);
    }
    @Override public void actionPerformed(ActionEvent e) {
        try {
            double value = ConverterSupport.parseAmount(amount, this);
            String cur = (String) target.getSelectedItem();
            String text = String.format("%.2f INR = %.2f %s", value, value * ConverterSupport.rate(cur), cur);
            result.setText(text); JOptionPane.showMessageDialog(this, text);
        } catch (NumberFormatException ex) { result.setText("Invalid amount"); }
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new ConverterFrameListener().setVisible(true)); }
}
