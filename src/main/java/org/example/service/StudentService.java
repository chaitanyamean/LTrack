package org.example.service;

import java.util.ArrayList;

import org.example.entity.Student;
import org.example.util.IdGenerator;
import org.example.exception.EntityNotFoundException;

public class StudentService {

    private ArrayList<Student> students;

    public StudentService() {
        students = new ArrayList<Student>();
    }

    public void addStudent(Student student) {
        student.setId(IdGenerator.getNextStudentId());
        students.add(student);
    }

    public ArrayList<Student> listStudents() {
        return students;
    }

    public Student findStudentById(int id) {

        for (int i = 0; i < students.size(); i++) {
            if (id == students.get(i).getId()) {
                return students.get(i);
            }
        }

        throw new EntityNotFoundException(
                "Student with ID " + id + " not found"
        );
    }

    public void updateStudent(Student student) {
        Student stu = findStudentById(student.getId());
        stu.setFirstName(student.getFirstName());
        stu.setLastName(student.getLastName());
        stu.setEmail(student.getEmail());
        stu.setActive(student.isActive());
        stu.setBatch(student.getBatch());

    }

    public void deactivateStudent(int id) {
        Student student = findStudentById(id);
        student.setActive(false);
    }

}
