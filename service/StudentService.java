import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private static final String STUDENTS_FILE = "students.txt";
    private static final String ATTENDANCE_FILE = "attendance.txt";
    private static final String GRADES_FILE = "grades.txt";

    public void addStudent(String id, String name, String semester) {
        Student s = new Student(id, name, semester);
        FileDatabase.appendLine(STUDENTS_FILE, s.toFileString());
        System.out.println("Student added.");
    }

    public List<Student> getAllStudents() {
        List<String> lines = FileDatabase.readLines(STUDENTS_FILE);
        List<Student> students = new ArrayList<>();
        for (String l : lines) {
            Student s = Student.fromFileString(l);
            if (s != null) {
                students.add(s);
            }
        }
        return students;
    }

    public Student findStudentById(String id) {
        List<Student> students = getAllStudents();
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public void markAttendance(String studentId, String date, String subject, boolean present) {
        AttendanceRecord r = new AttendanceRecord(studentId, date, subject, present);
        FileDatabase.appendLine(ATTENDANCE_FILE, r.toFileString());
        System.out.println("Attendance recorded.");
    }

    public List<AttendanceRecord> getAttendanceByStudent(String studentId) {
        List<String> lines = FileDatabase.readLines(ATTENDANCE_FILE);
        List<AttendanceRecord> records = new ArrayList<>();
        for (String l : lines) {
            AttendanceRecord r = AttendanceRecord.fromFileString(l);
            if (r != null && r.getStudentId().equals(studentId)) {
                records.add(r);
            }
        }
        return records;
    }

    public void addGrade(String studentId, String subject, String examType, double marks) {
        GradeRecord g = new GradeRecord(studentId, subject, examType, marks);
        FileDatabase.appendLine(GRADES_FILE, g.toFileString());
        System.out.println("Grade added.");
    }

    public List<GradeRecord> getGradesByStudent(String studentId) {
        List<String> lines = FileDatabase.readLines(GRADES_FILE);
        List<GradeRecord> grades = new ArrayList<>();
        for (String l : lines) {
            GradeRecord g = GradeRecord.fromFileString(l);
            if (g != null && g.getStudentId().equals(studentId)) {
                grades.add(g);
            }
        }
        return grades;
    }

    public List<GradeRecord> getAllGrades() {
        List<String> lines = FileDatabase.readLines(GRADES_FILE);
        List<GradeRecord> grades = new ArrayList<>();
        for (String l : lines) {
            GradeRecord g = GradeRecord.fromFileString(l);
            if (g != null) {
                grades.add(g);
            }
        }
        return grades;
    }

    public List<AttendanceRecord> getAllAttendance() {
        List<String> lines = FileDatabase.readLines(ATTENDANCE_FILE);
        List<AttendanceRecord> records = new ArrayList<>();
        for (String l : lines) {
            AttendanceRecord r = AttendanceRecord.fromFileString(l);
            if (r != null) {
                records.add(r);
            }
        }
        return records;
    }
}
