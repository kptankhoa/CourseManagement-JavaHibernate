package model;

import java.util.Objects;

public class Class {
    private String classId;
    private int total;
    private int totalMale;
    private int totalFemale;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalMale() {
        return totalMale;
    }

    public void setTotalMale(int totalMale) {
        this.totalMale = totalMale;
    }

    public int getTotalFemale() {
        return totalFemale;
    }

    public void setTotalFemale(int totalFemale) {
        this.totalFemale = totalFemale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return total == aClass.total && totalMale == aClass.totalMale && totalFemale == aClass.totalFemale && Objects.equals(classId, aClass.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, total, totalMale, totalFemale);
    }
}
