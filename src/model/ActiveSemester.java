package model;

import dao.SemesterDAO;

public class ActiveSemester {
    private static Semester activeSemester = null;

    public static Semester getActiveSemester() {
        return activeSemester;
    }

    //not change in db yet
    public static void setNewActiveSemester(Semester activeSemester) {
        Semester tmpSemester = ActiveSemester.getActiveSemester();
        tmpSemester.setActive(0);
        SemesterDAO.updateSemester(tmpSemester);
        activeSemester.setActive(1);
        SemesterDAO.updateSemester(activeSemester);
        ActiveSemester.activeSemester = activeSemester;
    }

    public static Semester getActiveSemesterFromDB(){
        activeSemester = SemesterDAO.getActiveSemester();
        return activeSemester;
    }
}
