package model;

import java.sql.Date;
import java.util.Objects;

public class Semester {
    private int semesterId;
    private String name;
    private String schoolYear;
    private Date startDate;
    private Date endDate;
    private int active;

    public Semester() {}

    public Semester(int semesterId, String name, String schoolYear, Date startDate, Date endDate, int active) {
        this.semesterId = semesterId;
        this.name = name;
        this.schoolYear = schoolYear;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
    }

    @Override
    public String toString() {
        return "Semester{" +
                "semesterId=" + semesterId +
                ", name='" + name + '\'' +
                ", schoolYear='" + schoolYear + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", active=" + active +
                '}';
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return semesterId == semester.semesterId && active == semester.active && name.equals(semester.name) && schoolYear.equals(semester.schoolYear) && startDate.equals(semester.startDate) && endDate.equals(semester.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(semesterId, name, schoolYear, startDate, endDate);
    }
}
