package assignment.task01;

import javax.swing.*;
import java.awt.*;

public class FeeWindow extends JFrame {
    private final JTextField regNo = new JTextField(14);
    private final JTextField amount = new JTextField(14);
    private final JLabel status = new JLabel("Enter details and select Pay.");

    public FeeWindow() {
        super("Student Fee Payment");
        setLayout(new BorderLayout(8, 8));
        JPanel fields = new JPanel(new GridLayout(2, 2, 8, 8));
        fields.add(new JLabel("Registration number:")); fields.add(regNo);
        fields.add(new JLabel("Amount:")); fields.add(amount);
        JPanel actions = new JPanel(new FlowLayout());
        JButton pay = new JButton("Pay"), clear = new JButton("Clear"), receipt = new JButton("Receipt");
        actions.add(pay); actions.add(clear); actions.add(receipt);
        add(fields, BorderLayout.CENTER); add(actions, BorderLayout.NORTH); add(status, BorderLayout.SOUTH);

        FeeService service = new FeeService(
                System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/csa09_assignment4"),
                System.getenv().getOrDefault("DB_USER", "root"),
                System.getenv().getOrDefault("DB_PASSWORD", ""));
        pay.addActionListener(e -> {
            pay.setEnabled(false); status.setText("Processing...");
            String id = regNo.getText(), value = amount.getText();
            new SwingWorker<Void, Void>() {
                private String message;
                @Override protected Void doInBackground() {
                    try { service.pay(id, value); message = "Payment recorded successfully."; }
                    catch (Exception ex) { message = ex.getMessage(); }
                    return null;
                }
                @Override protected void done() {
                    status.setText(message); pay.setEnabled(true);
                    if (message.startsWith("Payment")) JOptionPane.showMessageDialog(FeeWindow.this, message);
                    else JOptionPane.showMessageDialog(FeeWindow.this, message, "Payment error", JOptionPane.ERROR_MESSAGE);
                }
            }.execute();
        });
        clear.addActionListener(e -> { regNo.setText(""); amount.setText(""); status.setText("Cleared."); });
        receipt.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Receipt preview\nRegistration: " + regNo.getText() + "\nAmount: " + amount.getText()));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(430, 190); setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FeeWindow().setVisible(true));
    }
}
