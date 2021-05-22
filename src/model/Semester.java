package model;

import java.sql.Date;
import java.util.Objects;

public class Semester {
    private int semesterId;
    private String name;
    private String schoolYear;
    private Date startDate;
    private String endDay;

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

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return semesterId == semester.semesterId && Objects.equals(name, semester.name) && Objects.equals(schoolYear, semester.schoolYear) && Objects.equals(startDate, semester.startDate) && Objects.equals(endDay, semester.endDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(semesterId, name, schoolYear, startDate, endDay);
    }
}
