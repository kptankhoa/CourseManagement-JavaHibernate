package model;

import java.util.Objects;

public class Course {
    private String courseId;
    private String lecturer;
    private String room;

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
