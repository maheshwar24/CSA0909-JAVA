package assignment.task06;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RegistrationPanel extends JPanel {
    private static final int PAGE_SIZE = 50;
    private final RegistrationService service = new RegistrationService(new RegistrationDao());
    private final JTextField studentId = new JTextField(10), studentName = new JTextField(12);
    private final JComboBox<EventChoice> eventChoice = new JComboBox<>();
    private final JComboBox<String> filter = new JComboBox<>(new String[]{"All"});
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Student ID", "Student Name", "Event", "Registered At"}, 0) {
        @Override public boolean isCellEditable(int row, int column) { return false; }
    };
    private final JTable table = new JTable(model);
    private final JLabel status = new JLabel("Ready");
    private int page = 0;

    public RegistrationPanel() {
        super(new BorderLayout(8, 8));
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton register = new JButton("Register");
        form.add(new JLabel("Student ID:")); form.add(studentId);
        form.add(new JLabel("Name:")); form.add(studentName);
        form.add(eventChoice); form.add(register);
        add(form, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton prev = new JButton("Previous"), next = new JButton("Next"), export = new JButton("Export CSV");
        controls.add(new JLabel("Filter event:")); controls.add(filter);
        controls.add(prev); controls.add(next); controls.add(export);
        controls.add(status); add(controls, BorderLayout.SOUTH);

        register.addActionListener(e -> register());
        filter.addActionListener(e -> { page = 0; loadPage(); });
        prev.addActionListener(e -> { if (page > 0) { page--; loadPage(); } });
        next.addActionListener(e -> { page++; loadPage(); });
        export.addActionListener(e -> exportCsv());
        loadEvents();
    }

    private void loadEvents() {
        new SwingWorker<List<String[]>, Void>() {
            protected List<String[]> doInBackground() throws Exception { return service.events(); }
            protected void done() {
                try {
                    for (String[] event : get()) {
                        eventChoice.addItem(new EventChoice(Integer.parseInt(event[0]), event[1]));
                        filter.addItem(event[1]);
                    }
                    loadPage();
                } catch (Exception ex) { showError(ex); }
            }
        }.execute();
    }

    private void register() {
        EventChoice selected = (EventChoice) eventChoice.getSelectedItem();
        if (selected == null) { status.setText("No event available."); return; }
        String id = studentId.getText(), name = studentName.getText();
        status.setText("Registering...");
        new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws Exception {
                service.register(id, name, selected.id()); return null;
            }
            protected void done() {
                try { get(); status.setText("Registration successful."); studentId.setText(""); studentName.setText(""); page = 0; loadPage(); }
                catch (Exception ex) { showError(ex.getCause() == null ? ex : ex.getCause()); }
            }
        }.execute();
    }

    private void loadPage() {
        String selected = (String) filter.getSelectedItem();
        String event = selected == null ? "All" : selected;
        new SwingWorker<List<Object[]>, Void>() {
            protected List<Object[]> doInBackground() throws Exception { return service.page(event, PAGE_SIZE, page * PAGE_SIZE); }
            protected void done() {
                try {
                    model.setRowCount(0);
                    for (Object[] row : get()) model.addRow(row);
                    status.setText("Page " + (page + 1) + " | " + model.getRowCount() + " rows");
                } catch (Exception ex) { showError(ex); }
            }
        }.execute();
    }

    private void exportCsv() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
        String selected = (String) filter.getSelectedItem();
        String event = selected == null ? "All" : selected;
        new SwingWorker<List<Object[]>, Void>() {
            protected List<Object[]> doInBackground() throws Exception { return service.exportRows(event); }
            protected void done() {
                try (PrintWriter out = new PrintWriter(new OutputStreamWriter(
                        new FileOutputStream(chooser.getSelectedFile()), StandardCharsets.UTF_8))) {
                    out.println("ID,Student ID,Student Name,Event,Registered At");
                    for (Object[] row : get()) {
                        for (int i = 0; i < row.length; i++) {
                            if (i > 0) out.print(",");
                            out.print("\"" + String.valueOf(row[i]).replace("\"", "\"\"") + "\"");
                        }
                        out.println();
                    }
                    status.setText("CSV exported.");
                } catch (Exception ex) { showError(ex); }
            }
        }.execute();
    }

    private void showError(Exception ex) {
        Throwable cause = ex.getCause() == null ? ex : ex.getCause();
        status.setText("Operation failed.");
        JOptionPane.showMessageDialog(this, cause.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    private record EventChoice(int id, String name) {
        @Override public String toString() { return name; }
    }
}
