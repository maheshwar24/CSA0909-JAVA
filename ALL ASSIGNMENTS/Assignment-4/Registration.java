package assignment.task06;

import java.time.LocalDateTime;

public record Registration(long id, String studentId, String studentName,
                           int eventId, String eventName, LocalDateTime registeredAt) {}
