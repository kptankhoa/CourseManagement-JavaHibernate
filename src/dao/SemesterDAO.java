package dao;

import model.Semester;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;

public class SemesterDAO {
    public static ArrayList<Semester> getAllSemesters() {
        ArrayList<Semester> semesters = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from Semester";
            Query query = session.createQuery(hql);
            semesters = (ArrayList<Semester>) query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return semesters;
    }

    public static Semester getSemesterById(int semesterId) {
        Semester res = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select s from Semester s where s.semesterId = :semesterId";
            Query query = session.createQuery(hql);
            query.setString("semesterId", String.valueOf(semesterId));
            res = (Semester) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return res;
    }

    public static Semester getActiveSemester() {
        Semester res = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select s from Semester s where s.active = 1";
            Query query = session.createQuery(hql);
            res = (Semester) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return res;
    }

    public static Semester addSemester(Semester semester) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (SemesterDAO.getSemesterById(semester.getSemesterId()) != null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(semester);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return semester;
    }

    public static Semester updateSemester(Semester semester) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (SemesterDAO.getSemesterById(semester.getSemesterId()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(semester);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return semester;
    }

    public static Semester deleteSemester(Semester semester){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (SemesterDAO.getSemesterById(semester.getSemesterId()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(semester);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return semester;
    }
}
