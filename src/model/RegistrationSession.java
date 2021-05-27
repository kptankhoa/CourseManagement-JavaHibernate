package model;

import java.sql.Date;


public class RegistrationSession {
    private int sessionId;
    private Course course;
    private Date startDate;
    private Date endDate;

    public RegistrationSession() {
    }

    public RegistrationSession(int sessionId, Course course, Date startDate, Date endDate) {
        this.sessionId = sessionId;
        this.course = course;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "RegistrationSession{" +
                "sessionId=" + sessionId +
                ", course=" + course +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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


}
