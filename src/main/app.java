package main;

import dao.*;
import model.*;

import java.sql.Timestamp;


public class app {
    public static void main(String[] args) {
        Student kp = StudentDAO.getStudentById("1712541");
        Course c = CourseDAO.getCourseById("19_1-CSC13102");
        RegistrationPK pk = new RegistrationPK(c, kp);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(RegistrationDAO.addRegistration(new Registration(pk, timestamp)));

    }
}

