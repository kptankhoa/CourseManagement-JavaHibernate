package model;

import java.util.Objects;

public class Shift {
    private int id;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shift shift = (Shift) o;
        return id == shift.id && Objects.equals(time, shift.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time);
    }
}
