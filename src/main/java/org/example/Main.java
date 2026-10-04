package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.example.entity.Course;
import org.example.entity.Enrollment;
import org.example.entity.Student;
import org.example.exception.EntityNotFoundException;
import org.example.service.CourseService;
import org.example.service.EnrollmentService;
import org.example.service.StudentService;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();

        EnrollmentService enrollmentService
                = new EnrollmentService(studentService, courseService);

        boolean running = true;

        while (running) {

            System.out.println("\n===== LearnTrack =====");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("4. Exit");

            int choice = readInt(scanner, "Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        studentMenu(scanner, studentService);
                        break;

                    case 2:
                        courseMenu(scanner, courseService);
                        break;

                    case 3:
                        enrollmentMenu(
                                scanner,
                                enrollmentService,
                                studentService,
                                courseService
                        );
                        break;

                    case 4:
                        running = false;
                        System.out.println("Thank you for using LearnTrack.");
                        break;

                    default:
                        System.out.println("Invalid option.");
                }

            } catch (EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // =========================
    // STUDENT MENU
    // =========================
    private static void studentMenu(
            Scanner scanner,
            StudentService studentService) {

        boolean running = true;

        while (running) {

            System.out.println("\n===== Student Management =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Deactivate Student");
            System.out.println("6. Back");

            int choice = readInt(scanner, "Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        addStudent(scanner, studentService);
                        break;

                    case 2:
                        listStudents(studentService);
                        break;

                    case 3:
                        findStudent(scanner, studentService);
                        break;

                    case 4:
                        updateStudent(scanner, studentService);
                        break;

                    case 5:
                        deactivateStudent(scanner, studentService);
                        break;

                    case 6:
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }

            } catch (EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addStudent(
            Scanner scanner,
            StudentService studentService) {

        String firstName = readString(scanner, "First name: ");
        String lastName = readString(scanner, "Last name: ");
        String email = readString(scanner, "Email: ");
        int batch = readInt(scanner, "Batch: ");

        Student student = new Student(
                0,
                firstName,
                lastName,
                email,
                batch,
                true
        );

        studentService.addStudent(student);

        System.out.println(
                "Student added successfully. ID: " + student.getId()
        );
    }

    private static void listStudents(StudentService studentService) {

        ArrayList<Student> students = studentService.listStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n===== Students =====");

        for (Student student : students) {

            System.out.println(
                    "ID: " + student.getId()
                    + " | Name: " + student.getDisplayName()
                    + " | Email: " + student.getEmail()
                    + " | Active: " + student.isActive()
            );
        }
    }

    private static void findStudent(
            Scanner scanner,
            StudentService studentService) {

        int id = readInt(scanner, "Enter student ID: ");

        Student student = studentService.findStudentById(id);

        System.out.println("\nStudent Found:");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getDisplayName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Batch: " + student.getBatch());
        System.out.println("Active: " + student.isActive());
    }

    private static void updateStudent(
            Scanner scanner,
            StudentService studentService) {

        int id = readInt(scanner, "Enter student ID: ");

        Student student = studentService.findStudentById(id);

        String firstName = readString(
                scanner,
                "First name [" + student.getFirstName() + "]: "
        );

        String lastName = readString(
                scanner,
                "Last name [" + student.getLastName() + "]: "
        );

        String email = readString(
                scanner,
                "Email [" + student.getEmail() + "]: "
        );

        int batch = readInt(
                scanner,
                "Batch [" + student.getBatch() + "]: "
        );

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);

        studentService.updateStudent(student);

        System.out.println("Student updated successfully.");
    }

    private static void deactivateStudent(
            Scanner scanner,
            StudentService studentService) {

        int id = readInt(scanner, "Enter student ID: ");

        studentService.deactivateStudent(id);

        System.out.println("Student deactivated successfully.");
    }

    // =========================
    // COURSE MENU
    // =========================
    private static void courseMenu(
            Scanner scanner,
            CourseService courseService) {

        boolean running = true;

        while (running) {

            System.out.println("\n===== Course Management =====");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Search Course");
            System.out.println("4. Activate Course");
            System.out.println("5. Deactivate Course");
            System.out.println("6. Back");

            int choice = readInt(scanner, "Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        addCourse(scanner, courseService);
                        break;

                    case 2:
                        listCourses(courseService);
                        break;

                    case 3:
                        findCourse(scanner, courseService);
                        break;

                    case 4:
                        activateCourse(scanner, courseService);
                        break;

                    case 5:
                        deactivateCourse(scanner, courseService);
                        break;

                    case 6:
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }

            } catch (EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addCourse(
            Scanner scanner,
            CourseService courseService) {

        String name = readString(scanner, "Course name: ");
        String description = readString(scanner, "Description: ");
        int duration = readInt(scanner, "Duration in weeks: ");

        Course course = new Course(
                0,
                name,
                description,
                duration,
                true
        );

        courseService.addCourse(course);

        System.out.println(
                "Course added successfully. ID: " + course.getId()
        );
    }

    private static void listCourses(CourseService courseService) {

        ArrayList<Course> courses = courseService.listCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("\n===== Courses =====");

        for (Course course : courses) {

            System.out.println(
                    "ID: " + course.getId()
                    + " | Name: " + course.getCourseName()
                    + " | Duration: " + course.getDurationInWeeks()
                    + " weeks"
                    + " | Active: " + course.isActive()
            );
        }
    }

    private static void findCourse(
            Scanner scanner,
            CourseService courseService) {

        int id = readInt(scanner, "Enter course ID: ");

        Course course = courseService.findCourseById(id);

        System.out.println("\nCourse Found:");
        System.out.println("ID: " + course.getId());
        System.out.println("Name: " + course.getCourseName());
        System.out.println("Description: " + course.getDescription());
        System.out.println("Duration: " + course.getDurationInWeeks());
        System.out.println("Active: " + course.isActive());
    }

    private static void activateCourse(
            Scanner scanner,
            CourseService courseService) {

        int id = readInt(scanner, "Enter course ID: ");

        courseService.activateCourse(id);

        System.out.println("Course activated successfully.");
    }

    private static void deactivateCourse(
            Scanner scanner,
            CourseService courseService) {

        int id = readInt(scanner, "Enter course ID: ");

        courseService.deactivateCourse(id);

        System.out.println("Course deactivated successfully.");
    }

    // =========================
    // ENROLLMENT MENU
    // =========================
    private static void enrollmentMenu(
            Scanner scanner,
            EnrollmentService enrollmentService,
            StudentService studentService,
            CourseService courseService) {

        boolean running = true;

        while (running) {

            System.out.println("\n===== Enrollment Management =====");
            System.out.println("1. Enroll Student");
            System.out.println("2. View Student Enrollments");
            System.out.println("3. Complete Enrollment");
            System.out.println("4. Cancel Enrollment");
            System.out.println("5. Back");

            int choice = readInt(scanner, "Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        enrollStudent(
                                scanner,
                                enrollmentService,
                                studentService,
                                courseService
                        );
                        break;

                    case 2:
                        listStudentEnrollments(
                                scanner,
                                enrollmentService
                        );
                        break;

                    case 3:
                        completeEnrollment(
                                scanner,
                                enrollmentService
                        );
                        break;

                    case 4:
                        cancelEnrollment(
                                scanner,
                                enrollmentService
                        );
                        break;

                    case 5:
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }

            } catch (EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void enrollStudent(
            Scanner scanner,
            EnrollmentService enrollmentService,
            StudentService studentService,
            CourseService courseService) {

        int studentId = readInt(scanner, "Student ID: ");
        int courseId = readInt(scanner, "Course ID: ");

        // Validate first so the user gets a meaningful message
        studentService.findStudentById(studentId);
        courseService.findCourseById(courseId);

        Enrollment enrollment = new Enrollment(
                courseId,
                LocalDate.now(),
                0,
                "ACTIVE",
                studentId
        );

        enrollmentService.enrollStudent(enrollment);

        System.out.println(
                "Student enrolled successfully. Enrollment ID: "
                + enrollment.getId()
        );
    }

    private static void listStudentEnrollments(
            Scanner scanner,
            EnrollmentService enrollmentService) {

        int studentId = readInt(scanner, "Student ID: ");

        ArrayList<Enrollment> enrollments
                = enrollmentService.getEnrollmentsByStudentId(studentId);

        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }

        System.out.println("\n===== Enrollments =====");

        for (Enrollment enrollment : enrollments) {

            System.out.println(
                    "Enrollment ID: " + enrollment.getId()
                    + " | Course ID: " + enrollment.getCourseId()
                    + " | Date: " + enrollment.getEnrollmentDate()
                    + " | Status: " + enrollment.getStatus()
            );
        }
    }

    private static void completeEnrollment(
            Scanner scanner,
            EnrollmentService enrollmentService) {

        int id = readInt(scanner, "Enrollment ID: ");

        enrollmentService.completeEnrollment(id);

        System.out.println("Enrollment completed successfully.");
    }

    private static void cancelEnrollment(
            Scanner scanner,
            EnrollmentService enrollmentService) {

        int id = readInt(scanner, "Enrollment ID: ");

        enrollmentService.cancelEnrollment(id);

        System.out.println("Enrollment cancelled successfully.");
    }

    // =========================
    // INPUT HELPERS
    // =========================
    private static int readInt(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            try {

                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }

    private static String readString(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println(
                    "Input cannot be empty."
            );
        }
    }
}
