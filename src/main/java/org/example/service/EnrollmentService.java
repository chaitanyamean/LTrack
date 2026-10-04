package org.example.service;

import java.util.ArrayList;

import org.example.entity.Course;
import org.example.entity.Enrollment;
import org.example.entity.Student;
import org.example.exception.EntityNotFoundException;
import org.example.util.IdGenerator;

public class EnrollmentService {

    private ArrayList<Enrollment> enrollments;
    private StudentService studentService;
    private CourseService courseService;

    // public EnrollmentService() {
    //     enrollments = new ArrayList<Enrollment>();
    // }
    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.courseService = courseService;
        this.studentService = studentService;
        enrollments = new ArrayList<>();

    }

    public void addEnrollment(Enrollment enrollment) {
        enrollment.setId(IdGenerator.getNextEnrollmentId());
        enrollments.add(enrollment);
    }

    public Enrollment findEnrollmentById(int id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }
        throw new EntityNotFoundException(
                "Enrollment with ID " + id + " not found"
        );
    }

    public void enrollStudent(Enrollment enrollment) {

        Student student = studentService.findStudentById(enrollment.getStudentId());
        Course course = courseService.findCourseById(enrollment.getCourseId());

        if (student != null && course != null
                && student.isActive() == true && course.isActive() == true) {
            addEnrollment(enrollment);
        }

    }

    public ArrayList<Enrollment> getEnrollmentsByStudentId(int studentId) {

        ArrayList<Enrollment> enrollmentsForStudent = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                enrollmentsForStudent.add(enrollment);
            }
        }
        return enrollmentsForStudent;
    }

    public Enrollment completeEnrollment(int id) {
        Enrollment enrollment = findEnrollmentById(id);
        enrollment.setStatus("COMPLETED");

        return enrollment;
    }

    public Enrollment cancelEnrollment(int id) {
        Enrollment enrollment = findEnrollmentById(id);
        enrollment.setStatus("CANCELLED");

        return enrollment;
    }
}

//     add/enroll
// find enrollment
// get enrollments for a student
// complete enrollment
// cancel enrollment
