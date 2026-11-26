class ExamException extends Exception {
    ExamException(String message) { super(message); }
}
class ExamWindowClosedException extends ExamException {
    ExamWindowClosedException(String message) { super(message); }
}
class StudentNotRegisteredException extends ExamException {
    StudentNotRegisteredException(String message) { super(message); }
}
class SubmissionStorageException extends ExamException {
    SubmissionStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class ExamPortalDemo {
    static void submit(boolean windowOpen, boolean registered, int sizeKB)
            throws ExamException {
        if (!windowOpen)
            throw new ExamWindowClosedException("Exam window is closed.");
        if (!registered)
            throw new StudentNotRegisteredException("Student is not registered.");
        try {
            if (sizeKB > 500) throw new java.io.IOException("File exceeds 500 KB.");
            System.out.println("Submission saved successfully.");
        } catch (java.io.IOException e) {
            throw new SubmissionStorageException("Could not store submission.", e);
        }
    }

    public static void main(String[] args) {
        try {
            submit(true, true, 700);
        } catch (SubmissionStorageException e) {
            System.out.println(e.getMessage());
            System.out.println("Cause: " + e.getCause().getMessage());
        } catch (ExamException e) {
            System.out.println("Submission rejected: " + e.getMessage());
        }
    }
}
