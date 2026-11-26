package assignment.task03;

import javax.swing.*;
import java.awt.*;

public class LibrarySearchScreen extends JFrame {
    private final JTextField searchField = new JTextField(24);
    private final JComboBox<String> category = new JComboBox<>(new String[]{"All", "Book", "Journal", "Thesis"});
    private final DefaultListModel<String> resultsModel = new DefaultListModel<>();
    private final JList<String> results = new JList<>(resultsModel);
    private final JLabel status = new JLabel("Enter a title or keyword.");

    public LibrarySearchScreen() {
        super("Campus Library Search");
        setLayout(new BorderLayout(8, 8));

        JPanel title = new JPanel(new FlowLayout(FlowLayout.LEFT));
        title.add(new JLabel("Campus Library"));
        JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton searchButton = new JButton("Search");
        search.add(new JLabel("Search:")); search.add(searchField);
        search.add(category); search.add(searchButton);

        JPanel top = new JPanel(new BorderLayout());
        top.add(title, BorderLayout.NORTH); top.add(search, BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);
        results.setVisibleRowCount(10);
        add(new JScrollPane(results), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        JPanel buttons = new JPanel(new FlowLayout());
        JButton prev = new JButton("Prev"), next = new JButton("Next"), reserve = new JButton("Reserve");
        buttons.add(prev); buttons.add(next); buttons.add(reserve);
        bottom.add(buttons, BorderLayout.NORTH); bottom.add(status, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> doSearch());
        searchField.addActionListener(e -> doSearch());
        prev.addActionListener(e -> status.setText("Previous page requested."));
        next.addActionListener(e -> status.setText("Next page requested."));
        reserve.addActionListener(e -> {
            String selected = results.getSelectedValue();
            status.setText(selected == null ? "Select a result first." : "Reservation request: " + selected);
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 350));
        setSize(760, 500);
        setLocationRelativeTo(null);
    }

    private void doSearch() {
        String query = searchField.getText().trim();
        resultsModel.clear();
        if (query.isEmpty()) { status.setText("Enter a search keyword."); return; }
        // Demonstration data; replace with JDBC search for a database-backed library.
        resultsModel.addElement("Java Programming – Book (sample result for: " + query + ")");
        resultsModel.addElement("Event-Driven GUI Design – Journal (sample result)");
        status.setText("Category: " + category.getSelectedItem() + " | " + resultsModel.size() + " sample results");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibrarySearchScreen().setVisible(true));
    }
}
