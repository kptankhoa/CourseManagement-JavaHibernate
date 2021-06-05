package utils;

import dao.RegistrationSessionDAO;
import model.RegistrationSession;
import model.Semester;

import java.sql.Date;
import java.util.ArrayList;

public class TimeValidateUtil {
    public static boolean isDateValid(Semester activeSemester) {
        ArrayList<RegistrationSession> activeSemesterSessions = RegistrationSessionDAO.getRegistrationSessionsBySemesterId(activeSemester.getSemesterId());
        if(!activeSemesterSessions.isEmpty()){
            java.util.Date utilDate = new java.util.Date();
            Date currentDate = new Date(utilDate.getTime());
            for (RegistrationSession session : activeSemesterSessions){
                if((currentDate.compareTo(session.getStartDate())>0)&&(currentDate.compareTo(session.getEndDate())<0)){
                    return true;
                }
            }
        }
        return false;
    }
}
