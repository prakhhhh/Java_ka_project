import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyticsService {

    private StudentService studentService;

    public AnalyticsService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void showAttendancePercentage() {
        List<Student> students = studentService.getAllStudents();
        List<AttendanceRecord> all = studentService.getAllAttendance();

        if (all.isEmpty()) {
            System.out.println("No attendance records found.");
            return;
        }

        Map<String, int[]> map = new HashMap<>(); // id -> [present, total]

        for (AttendanceRecord r : all) {
            int[] arr = map.getOrDefault(r.getStudentId(), new int[]{0, 0});
            if (r.isPresent()) arr[0]++;
            arr[1]++;
            map.put(r.getStudentId(), arr);
        }

        System.out.println("Attendance Percentage:");
        for (Student s : students) {
            int[] arr = map.get(s.getId());
            if (arr == null || arr[1] == 0) {
                System.out.println(s.getId() + " - " + s.getName() + " : No records");
            } else {
                double percentage = (arr[0] * 100.0) / arr[1];
                System.out.printf("%s - %s : %.2f%%%n", s.getId(), s.getName(), percentage);
            }
        }
    }

    public void showTopPerformer() {
        List<Student> students = studentService.getAllStudents();
        List<GradeRecord> allGrades = studentService.getAllGrades();

        if (allGrades.isEmpty()) {
            System.out.println("No grade records found.");
            return;
        }

        Map<String, double[]> map = new HashMap<>(); // id -> [total, count]

        for (GradeRecord g : allGrades) {
            double[] arr = map.getOrDefault(g.getStudentId(), new double[]{0.0, 0.0});
            arr[0] += g.getMarks();
            arr[1] += 1;
            map.put(g.getStudentId(), arr);
        }

        String topId = null;
        double bestAvg = -1.0;

        for (Map.Entry<String, double[]> e : map.entrySet()) {
            double avg = e.getValue()[0] / e.getValue()[1];
            if (avg > bestAvg) {
                bestAvg = avg;
                topId = e.getKey();
            }
        }

        if (topId == null) {
            System.out.println("No top performer found.");
            return;
        }

        Student top = null;
        for (Student s : students) {
            if (s.getId().equals(topId)) {
                top = s;
                break;
            }
        }

        if (top != null) {
            System.out.printf("Top Performer: %s - %s with average %.2f%n",
                    top.getId(), top.getName(), bestAvg);
        } else {
            System.out.println("Top performer student details not found.");
        }
    }
}
