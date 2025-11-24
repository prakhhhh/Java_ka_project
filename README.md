# Student Attendance & Performance Tracker - Java CLI

## About

The **Student Attendance & Performance Tracker** is a Java-based command line interface (CLI) application designed to help teachers manage student data in a simple and efficient way.
Using this tool, a teacher can:

- Register/login as a user
- Add students and subjects
- Record daily attendance
- Store marks/grades for assessments
- View basic analytics such as attendance percentage and top performers

This project is created as part of the **VITyarthi - Build Your Own Project** flipped course evaluation.

## Features

- **User Management**
  - Register a new teacher account
  - Login with username & password (simple file-based auth)

- **Student Management**
  - Add new students (ID, name, semester)
  - View list of students

- **Attendance Management**
  - Mark attendance for a selected date and subject
  - View attendance records of a student
  - Calculate attendance percentage

- **Performance Management**
  - Add marks for a student in a subject and exam type
  - View marks of a student
  - Compute average marks per subject

- **Analytics & Reports**
  - Attendance percentage per student
  - Top performing student based on average marks

- **Data Persistence**
  - All data (users, students, attendance, grades) is stored in simple text files under the `data/` folder.

## Technologies / Tools Used

- **Language:** Java (JDK 8+)
- **Paradigm:** Object-Oriented Programming (OOP)
- **Data Storage:** Text files (file-based storage)
- **Tools:** Any Java-supported IDE or simple terminal with `javac` and `java`

## Installation and Setup

1. Make sure you have **Java JDK 8 or higher** installed.
2. Clone or download this repository:

   ```bash
   git clone https://github.com/<your-username>/student-tracker.git
   cd student-tracker
   ```

3. Ensure the folder structure is preserved, especially `model/`, `service/`, `db/`, `util/`, and `data/`.

4. Compile the Java files from the project root:

   ```bash
   javac Main.java model/*.java service/*.java db/*.java util/*.java
   ```

5. Run the program:

   ```bash
   java Main
   ```

## Usage

1. When you run the program, you will see a **welcome menu**:
   - Register a new user
   - Login
   - Exit

2. After logging in, the **main menu** will allow you to:
   - Manage students (add / view)
   - Manage attendance (mark / view)
   - Manage grades (add / view)
   - View analytics (attendance %, top performer)
   - Logout / Exit

3. Data is automatically stored in the `data/` text files when you perform operations.

## File Structure

```text
student-tracker/
├── model/
│   ├── User.java
│   ├── Student.java
│   ├── AttendanceRecord.java
│   └── GradeRecord.java
├── service/
│   ├── UserService.java
│   ├── StudentService.java
│   └── AnalyticsService.java
├── db/
│   └── FileDatabase.java
├── util/
│   └── InputHelper.java
├── data/
│   ├── users.txt
│   ├── students.txt
│   ├── attendance.txt
│   └── grades.txt
├── Main.java
├── README.md
└── statement.md
```

## Testing

- Basic manual testing can be done by:
  - Registering and logging in with different users
  - Adding multiple students
  - Marking attendance on different dates
  - Adding marks for different exams
  - Verifying that analytics calculations (attendance % and averages) look correct.

## Future Enhancements

- Role-based access (e.g., Admin vs Faculty)
- Export reports to CSV/PDF
- GUI version using JavaFX or a web front-end
- Database storage using MySQL instead of text files
