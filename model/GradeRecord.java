public class GradeRecord {
    private String studentId;
    private String subject;
    private String examType; // e.g., "Midterm", "EndSem"
    private double marks;

    public GradeRecord(String studentId, String subject, String examType, double marks) {
        this.studentId = studentId;
        this.subject = subject;
        this.examType = examType;
        this.marks = marks;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSubject() {
        return subject;
    }

    public String getExamType() {
        return examType;
    }

    public double getMarks() {
        return marks;
    }

    public String toFileString() {
        return studentId + "," + subject + "," + examType + "," + marks;
    }

    public static GradeRecord fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 4) return null;
        double m = Double.parseDouble(parts[3]);
        return new GradeRecord(parts[0], parts[1], parts[2], m);
    }
}
