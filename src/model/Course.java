package model;

import java.util.Objects;

public class Course {
    private String courseId;
    private Subject subject;
    private Semester semester;
    private String lecturer;
    private String room;
    private Shift shift;
    private int slots;

    public Course() {
        super();
    }

    public Course(String courseId, Subject subject, Semester semester, String lecturer, String room, Shift shift, int slots) {
        this.courseId = courseId;
        this.subject = subject;
        this.semester = semester;
        this.lecturer = lecturer;
        this.room = room;
        this.shift = shift;
        this.slots = slots;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", subject=" + subject +
                ", semester=" + semester +
                ", lecturer='" + lecturer + '\'' +
                ", room='" + room + '\'' +
                ", shift=" + shift + '\'' +
                "slots=" + slots +
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

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId) && Objects.equals(subject, course.subject) && Objects.equals(semester, course.semester) && Objects.equals(lecturer, course.lecturer) && Objects.equals(room, course.room) && Objects.equals(shift, course.shift);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, subject, semester, lecturer, room, shift);
    }
}
