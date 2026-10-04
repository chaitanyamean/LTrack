# LearnTrack

LearnTrack is a console-based Student and Course Management System built using Core Java.

The application allows administrators to:

- Manage students
- Manage courses
- Manage enrollments
- Activate/deactivate students and courses
- Complete or cancel enrollments

The project is built to practice fundamental Java and Object-Oriented Programming concepts.

## Technologies Used

- Java
- Core Java
- ArrayList
- Exception Handling
- Object-Oriented Programming

## Project Structure

```text
src/
└── org/
    └── example/
        ├── Main.java
        │
        ├── entity/
        │   ├── Person.java
        │   ├── Student.java
        │   ├── Course.java
        │   └── Enrollment.java
        │
        ├── service/
        │   ├── StudentService.java
        │   ├── CourseService.java
        │   └── EnrollmentService.java
        │
        ├── util/
        │   └── IdGenerator.java
        │
        └── exception/
            └── EntityNotFoundException.java