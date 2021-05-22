package model;

import java.sql.Date;
import java.util.Objects;

public class Student {
    private String studentId;
    private String name;
    private String gender;
    private Date dob;
    private String username;
    private Clazz clazz;

    public Student() {
    }

    public Student(String studentId, String name, String gender, Date dob, String username, Clazz clazz) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.username = username;
        this.clazz = clazz;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getClassId() {
//        return classId;
//    }
//
//    public void setClassId(String classId) {
//        this.classId = classId;
//    }


    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId) && Objects.equals(name, student.name) && Objects.equals(gender, student.gender) && Objects.equals(dob, student.dob) && Objects.equals(username, student.username) && Objects.equals(clazz, student.clazz);
    }


    @Override
    public int hashCode() {
        return Objects.hash(studentId, name, gender, dob, username, clazz);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", username='" + username + '\'' +
                ", class='" + clazz.getClassId() + '\'' +
                '}';
    }
}
