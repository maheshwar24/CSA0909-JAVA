package assignment.task06;

import java.sql.*;
import java.util.*;

public class RegistrationDao {
    public List<String[]> getEvents() throws SQLException {
        List<String[]> rows = new ArrayList<>();
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT event_id, event_name FROM events ORDER BY event_name");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) rows.add(new String[]{rs.getString(1), rs.getString(2)});
        }
        return rows;
    }

    public void register(String studentId, String studentName, int eventId) throws SQLException {
        String sql = "INSERT INTO registrations(student_id, student_name, event_id) VALUES (?, ?, ?)";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, studentId); ps.setString(2, studentName); ps.setInt(3, eventId);
            ps.executeUpdate();
        }
    }

    public List<Object[]> page(String eventFilter, int limit, int offset) throws SQLException {
        String sql = "SELECT r.registration_id, r.student_id, r.student_name, e.event_name, r.registered_at " +
                "FROM registrations r JOIN events e ON r.event_id=e.event_id " +
                "WHERE (? = 'All' OR e.event_name = ?) ORDER BY r.registration_id DESC LIMIT ? OFFSET ?";
        List<Object[]> rows = new ArrayList<>();
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, eventFilter); ps.setString(2, eventFilter); ps.setInt(3, limit); ps.setInt(4, offset);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) rows.add(new Object[]{rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getTimestamp(5)});
            }
        }
        return rows;
    }

    public List<Object[]> allForExport(String eventFilter) throws SQLException {
        String sql = "SELECT r.registration_id, r.student_id, r.student_name, e.event_name, r.registered_at " +
                "FROM registrations r JOIN events e ON r.event_id=e.event_id " +
                "WHERE (? = 'All' OR e.event_name = ?) ORDER BY r.registration_id DESC";
        List<Object[]> rows = new ArrayList<>();
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, eventFilter); ps.setString(2, eventFilter);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) rows.add(new Object[]{rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getTimestamp(5)});
            }
        }
        return rows;
    }
}
