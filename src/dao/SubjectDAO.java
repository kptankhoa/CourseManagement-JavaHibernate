package dao;

import model.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;

public class SubjectDAO {
    public static ArrayList<Subject> getAllSubjects() {
        ArrayList<Subject> subjects = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from Subject";
            Query query = session.createQuery(hql);
            subjects = (ArrayList<Subject>) query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return subjects;
    }

    public static Subject getSubjectById(String subjectId) {
        Subject res = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select s from Subject s where s.subjectId = :subjectId";
            Query query = session.createQuery(hql);
            query.setString("subjectId", subjectId);
            res = (Subject) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return res;
    }

    public static Subject addSubject(Subject subject) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (SubjectDAO.getSubjectById(subject.getSubjectId()) != null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(subject);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return subject;
    }

    public static Subject updateSubject(Subject subject) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (SubjectDAO.getSubjectById(subject.getSubjectId()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(subject);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return subject;
    }

    public static Subject deleteSubject(Subject subject){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (SubjectDAO.getSubjectById(subject.getSubjectId()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(subject);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return subject;
    }
}
