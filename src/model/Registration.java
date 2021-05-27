package model;

import java.sql.Timestamp;

public class Registration {
    private RegistrationPK pk;
    private Timestamp registrationTime;

    public Registration() {
    }



    public Registration(RegistrationPK pk, Timestamp registrationTime) {
        this.pk = pk;
        this.registrationTime = registrationTime;
    }

    public RegistrationPK getPk() {
        return pk;
    }

    public void setPk(RegistrationPK pk) {
        this.pk = pk;
    }

    public Timestamp getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime) {
        this.registrationTime = registrationTime;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "pk=" + pk +
                ", registrationTime=" + registrationTime +
                '}';
    }
}
