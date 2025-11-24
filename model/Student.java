public class Student {
    private String id;
    private String name;
    private String semester;

    public Student(String id, String name, String semester) {
        this.id = id;
        this.name = name;
        this.semester = semester;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSemester() {
        return semester;
    }

    public String toFileString() {
        return id + "," + name + "," + semester;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 3) return null;
        return new Student(parts[0], parts[1], parts[2]);
    }
}
