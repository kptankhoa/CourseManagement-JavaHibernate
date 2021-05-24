package model;

import java.util.Objects;

public class Course {
    private String courseId;
    private Subject subject;
    private Semester semester;
    private String lecturer;
    private String room;
    private Shift shift;

    public Course() {
    }

    public Course(String courseId, Subject subject, Semester semester, String lecturer, String room, Shift shift) {
        this.courseId = courseId;
        this.subject = subject;
        this.semester = semester;
        this.lecturer = lecturer;
        this.room = room;
        this.shift = shift;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", subject=" + subject +
                ", semester=" + semester +
                ", lecturer='" + lecturer + '\'' +
                ", room='" + room + '\'' +
                ", shift=" + shift +
                '}';
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId) && Objects.equals(lecturer, course.lecturer) && Objects.equals(room, course.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, lecturer, room);
    }


}
