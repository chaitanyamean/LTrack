package org.example.util;

public class IdGenerator {

    private static int studentIdCounter = 100;
    private static int courseIdCounter = 100;
    private static int enrollmentIdCounter = 100;

    public IdGenerator() {
    }

    public static int getNextStudentId() {

        return ++studentIdCounter;
    }

    public static int getNextCourseId() {
        return ++courseIdCounter;
    }

    public static int getNextEnrollmentId() {
        return ++enrollmentIdCounter;
    }

}
