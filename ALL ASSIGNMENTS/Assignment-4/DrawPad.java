package assignment.task04;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class DrawPad extends Frame {
    private final List<Point> points = new ArrayList<>();
    private final Label status = new Label("Clicks: 0");

    public DrawPad() {
        super("Draw Pad");
        add(status, BorderLayout.SOUTH);
        addMouseListener(new MouseAdapter() {
            // TODO 1 completed: correct override, store point, update label and repaint.
            @Override public void mouseClicked(MouseEvent e) {
                points.add(e.getPoint());
                status.setText("Clicks: " + points.size());
                repaint();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
        });
        setSize(400, 300);
    }

    @Override public void paint(Graphics g) {
        // TODO 2 completed: redraw all stored points on every paint.
        for (Point point : points) {
            g.fillOval(point.x - 3, point.y - 3, 6, 6);
        }
        status.repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new DrawPad().setVisible(true));
    }
}
