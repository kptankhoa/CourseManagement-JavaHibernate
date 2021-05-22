package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Registration {
    private String studentId;
    private String courseId;
    private Timestamp registrationTime;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Timestamp getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime) {
        this.registrationTime = registrationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId) && Objects.equals(registrationTime, that.registrationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId, registrationTime);
    }
}
