package main;

import dao.SubjectDAO;
import model.Subject;


public class app {
    public static void main(String[] args) {
//        ArrayList<Subject> sjs =SubjectDAO.getAllSubjects();
//        sjs.forEach(sj-> System.out.println(sj.toString()));

//        Subject sj = SubjectDAO.getSubjectById("CSC13102");
//        System.out.println(sj.toString());

        Subject sj = SubjectDAO.addSubject(new Subject("CSC13115", "Các công nghệ mới trong phát triển phần mềm", 4));
        if (sj!=null){
            System.out.println(sj.toString());
        } else{
            System.out.println("bluhh");
        }
    }
}

