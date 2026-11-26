package assignment.task06;

import java.sql.SQLException;
import java.util.List;

public class RegistrationService {
    private final RegistrationDao dao;
    public RegistrationService(RegistrationDao dao) { this.dao = dao; }

    public void register(String studentId, String studentName, int eventId) throws SQLException {
        if (studentId == null || studentId.isBlank()) throw new IllegalArgumentException("Student ID is required.");
        if (studentName == null || studentName.isBlank()) throw new IllegalArgumentException("Student name is required.");
        if (eventId <= 0) throw new IllegalArgumentException("Select an event.");
        try { dao.register(studentId.trim(), studentName.trim(), eventId); }
        catch (SQLException ex) {
            if ("23000".equals(ex.getSQLState()))
                throw new IllegalArgumentException("This student is already registered for that event.");
            throw ex;
        }
    }
    public List<String[]> events() throws SQLException { return dao.getEvents(); }
    public List<Object[]> page(String event, int limit, int offset) throws SQLException { return dao.page(event, limit, offset); }
    public List<Object[]> exportRows(String event) throws SQLException { return dao.allForExport(event); }
}
