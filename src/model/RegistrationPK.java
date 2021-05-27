package model;

import java.io.Serializable;
import java.util.Objects;

public class RegistrationPK implements Serializable {
    private Course course;
    private Student student;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public RegistrationPK() {
        super();
    }

    public RegistrationPK(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    @Override
    public String toString() {
        return "RegistrationPK{" +
                "course=" + course +
                ", student=" + student +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationPK that = (RegistrationPK) o;
        return Objects.equals(course, that.course) && Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, student);
    }
}
