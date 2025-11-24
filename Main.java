import java.util.List;

public class Main {

    public static void main(String[] args) {

        InputHelper input = new InputHelper();
        UserService userService = new UserService();
        StudentService studentService = new StudentService();
        AnalyticsService analyticsService = new AnalyticsService(studentService);

        boolean exit = false;
        boolean loggedIn = false;

        System.out.println("==========================================");
        System.out.println(" Student Attendance & Performance Tracker ");
        System.out.println("==========================================");

        while (!exit) {
            if (!loggedIn) {
                System.out.println("\n1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                int choice = input.readInt("Enter choice: ");

                switch (choice) {
                    case 1:
                        String rUser = input.readLine("Enter username: ");
                        String rPass = input.readLine("Enter password: ");
                        userService.register(rUser, rPass);
                        break;
                    case 2:
                        String lUser = input.readLine("Enter username: ");
                        String lPass = input.readLine("Enter password: ");
                        if (userService.login(lUser, lPass)) {
                            loggedIn = true;
                            System.out.println("Login successful.");
                        } else {
                            System.out.println("Invalid credentials.");
                        }
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("\n---- Main Menu ----");
                System.out.println("1. Manage Students");
                System.out.println("2. Manage Attendance");
                System.out.println("3. Manage Grades");
                System.out.println("4. Analytics");
                System.out.println("5. Logout");
                int choice = input.readInt("Enter choice: ");

                switch (choice) {
                    case 1:
                        manageStudents(input, studentService);
                        break;
                    case 2:
                        manageAttendance(input, studentService);
                        break;
                    case 3:
                        manageGrades(input, studentService);
                        break;
                    case 4:
                        manageAnalytics(input, analyticsService);
                        break;
                    case 5:
                        loggedIn = false;
                        System.out.println("Logged out.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }

        System.out.println("Exiting... Goodbye.");
    }

    private static void manageStudents(InputHelper input, StudentService service) {
        System.out.println("\n-- Student Management --");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        int choice = input.readInt("Enter choice: ");

        switch (choice) {
            case 1:
                String id = input.readLine("Enter student ID: ");
                String name = input.readLine("Enter student name: ");
                String sem = input.readLine("Enter semester: ");
                service.addStudent(id, name, sem);
                break;
            case 2:
                List<Student> students = service.getAllStudents();
                System.out.println("Students:");
                for (Student s : students) {
                    System.out.println(s.getId() + " - " + s.getName() + " (Sem " + s.getSemester() + ")");
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageAttendance(InputHelper input, StudentService service) {
        System.out.println("\n-- Attendance Management --");
        System.out.println("1. Mark Attendance");
        System.out.println("2. View Attendance of a Student");
        int choice = input.readInt("Enter choice: ");

        switch (choice) {
            case 1:
                String id = input.readLine("Enter student ID: ");
                Student s = service.findStudentById(id);
                if (s == null) {
                    System.out.println("Student not found.");
                    return;
                }
                String date = input.readLine("Enter date (yyyy-mm-dd): ");
                String subject = input.readLine("Enter subject: ");
                String presentStr = input.readLine("Present? (y/n): ");
                boolean present = presentStr.equalsIgnoreCase("y");
                service.markAttendance(id, date, subject, present);
                break;
            case 2:
                String sid = input.readLine("Enter student ID: ");
                Student st = service.findStudentById(sid);
                if (st == null) {
                    System.out.println("Student not found.");
                    return;
                }
                java.util.List<AttendanceRecord> records = service.getAttendanceByStudent(sid);
                System.out.println("Attendance for " + st.getName() + ":");
                for (AttendanceRecord r : records) {
                    System.out.println(r.getDate() + " - " + r.getSubject() + " : " + (r.isPresent() ? "Present" : "Absent"));
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageGrades(InputHelper input, StudentService service) {
        System.out.println("\n-- Grades Management --");
        System.out.println("1. Add Grade");
        System.out.println("2. View Grades of a Student");
        int choice = input.readInt("Enter choice: ");

        switch (choice) {
            case 1:
                String id = input.readLine("Enter student ID: ");
                Student s = service.findStudentById(id);
                if (s == null) {
                    System.out.println("Student not found.");
                    return;
                }
                String subject = input.readLine("Enter subject: ");
                String exam = input.readLine("Enter exam type: ");
                double marks = input.readDouble("Enter marks: ");
                service.addGrade(id, subject, exam, marks);
                break;
            case 2:
                String sid = input.readLine("Enter student ID: ");
                Student st = service.findStudentById(sid);
                if (st == null) {
                    System.out.println("Student not found.");
                    return;
                }
                java.util.List<GradeRecord> grades = service.getGradesByStudent(sid);
                System.out.println("Grades for " + st.getName() + ":");
                for (GradeRecord g : grades) {
                    System.out.println(g.getSubject() + " (" + g.getExamType() + ") : " + g.getMarks());
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageAnalytics(InputHelper input, AnalyticsService analytics) {
        System.out.println("\n-- Analytics --");
        System.out.println("1. Attendance Percentage");
        System.out.println("2. Top Performer");
        int choice = input.readInt("Enter choice: ");

        switch (choice) {
            case 1:
                analytics.showAttendancePercentage();
                break;
            case 2:
                analytics.showTopPerformer();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
