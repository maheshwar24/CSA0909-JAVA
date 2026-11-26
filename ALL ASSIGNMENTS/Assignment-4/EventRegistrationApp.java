package assignment.task06;

import javax.swing.*;
import java.awt.event.*;

public class EventRegistrationApp extends JFrame {
    public EventRegistrationApp() {
        super("Campus Event Registration");
        setJMenuBar(createMenuBar());
        setContentPane(new RegistrationPanel());
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(EventRegistrationApp.this,
                        "Close the registration application?", "Confirm close", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) dispose();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(1000, 600); setLocationRelativeTo(null);
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        file.add(exit); bar.add(file);
        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Campus Event Registration – CSA09 Assignment 4"));
        help.add(about); bar.add(help);
        return bar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventRegistrationApp().setVisible(true));
    }
}
