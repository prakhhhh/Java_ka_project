public class AttendanceRecord {
    private String studentId;
    private String date;    // yyyy-mm-dd
    private String subject;
    private boolean present;

    public AttendanceRecord(String studentId, String date, String subject, boolean present) {
        this.studentId = studentId;
        this.date = date;
        this.subject = subject;
        this.present = present;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isPresent() {
        return present;
    }

    public String toFileString() {
        return studentId + "," + date + "," + subject + "," + (present ? "1" : "0");
    }

    public static AttendanceRecord fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 4) return null;
        boolean p = "1".equals(parts[3]);
        return new AttendanceRecord(parts[0], parts[1], parts[2], p);
    }
}
