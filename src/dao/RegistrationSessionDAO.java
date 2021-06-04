package dao;

import model.Registration;
import model.RegistrationSession;
import model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;

public class RegistrationSessionDAO {
    public static ArrayList<RegistrationSession> getAllRegistrationSessions(){
        ArrayList<RegistrationSession> rSessions = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from RegistrationSession r left join fetch r.semester";
            Query query = session.createQuery(hql);
            rSessions = (ArrayList<RegistrationSession>) query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return rSessions;
    }

    public static RegistrationSession getRegistrationSessionById(int sessionId){
        RegistrationSession res = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select r from RegistrationSession r where r.sessionId = :sessionId";
            Query query = session.createQuery(hql);
            query.setString("sessionId", String.valueOf(sessionId));
            res = (RegistrationSession) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return res;
    }

    public static RegistrationSession addRegistrationSession(RegistrationSession rSession){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (RegistrationSessionDAO.getRegistrationSessionById(rSession.getSessionId()) != null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(rSession);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return rSession;
    }

    public static RegistrationSession updateRegistrationSession(RegistrationSession rSession){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (RegistrationSessionDAO.getRegistrationSessionById(rSession.getSessionId()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(rSession);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return rSession;
    }

    public static RegistrationSession deleteRegistrationSession(RegistrationSession rSession){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (RegistrationSessionDAO.getRegistrationSessionById(rSession.getSessionId()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(rSession);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return rSession;
    }

}
