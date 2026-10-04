package org.example.service;

import java.util.ArrayList;

import org.example.entity.Course;
import org.example.exception.EntityNotFoundException;
import org.example.util.IdGenerator;

public class CourseService {

    private ArrayList<Course> courses;

    public CourseService() {
        courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        course.setId(IdGenerator.getNextCourseId());
        courses.add(course);
    }

    public ArrayList<Course> listCourses() {
        return courses;
    }

    public Course findCourseById(int id) {

        for (int i = 0; i < courses.size(); i++) {
            if (id == courses.get(i).getId()) {
                return courses.get(i);
            }
        }

        throw new EntityNotFoundException(
                "Course with ID " + id + " not found"
        );
    }

    public void deactivateCourse(int id) {
        Course course = findCourseById(id);
        course.setActive(false);
    }

    public void activateCourse(int id) {
        Course course = findCourseById(id);
        course.setActive(true);
    }

}
