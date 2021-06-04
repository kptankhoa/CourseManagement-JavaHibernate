package model;

import java.sql.Date;


public class RegistrationSession {
    private int sessionId;
    private Semester semester;
    private Date startDate;
    private Date endDate;

    public RegistrationSession() {
    }

    public RegistrationSession(int sessionId, Semester semester, Date startDate, Date endDate) {
        this.sessionId = sessionId;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
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
