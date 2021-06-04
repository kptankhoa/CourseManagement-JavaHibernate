package dao;

import model.Registration;
import model.RegistrationPK;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;

public class RegistrationDAO {
    public static ArrayList<Registration> getAllRegistrations() {
        ArrayList<Registration> registrations = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from Registration";
            Query query = session.createQuery(hql);
            registrations = (ArrayList<Registration>) query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return registrations;
    }

    public static ArrayList<Registration> getRegistrationsByStudentId(String studentId) {
        ArrayList<Registration> registrations = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select r from Registration r where r.pk.student.studentId = :studentId";
            Query query = session.createQuery(hql);
            query.setString("studentId", studentId);
            registrations = (ArrayList<Registration>) query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return registrations;
    }

    public static ArrayList<Registration> getRegistrationByCourseId(String courseId) {
        ArrayList<Registration> registrations = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select r from Registration r where r.pk.course.courseId = :courseId";
            Query query = session.createQuery(hql);
            query.setString("courseId", courseId);
            registrations = (ArrayList<Registration>) query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return registrations;
    }

    public static ArrayList<Registration> getRegistrationBySemesterIdNStudentId(int semesterId, String studentId) {
        ArrayList<Registration> registrations = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select r from Registration r where r.pk.course.semester.semesterId = :semesterId and r.pk.student.studentId = :studentId";
            Query query = session.createQuery(hql);
            query.setString("semesterId", String.valueOf(semesterId));
            query.setString("studentId", studentId);
            registrations = (ArrayList<Registration>) query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return registrations;
    }

    public static Registration getRegistrationByPk(RegistrationPK pk) {
        String studentId = pk.getStudent().getStudentId();
        String courseId = pk.getCourse().getCourseId();
        Registration res = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select r from Registration r where r.pk.course.courseId = :courseId and r.pk.student.studentId = :studentId";
            Query query = session.createQuery(hql);
            query.setString("studentId", studentId);
            query.setString("courseId", courseId);
            res = (Registration) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return res;
    }

    public static Registration addRegistration(Registration registration) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (RegistrationDAO.getRegistrationByPk(registration.getPk()) != null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(registration);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return registration;
    }

    public static Registration updateRegistration(Registration registration) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (RegistrationDAO.getRegistrationByPk(registration.getPk()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(registration);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return registration;
    }

    public static Registration deleteRegistration(Registration registration) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (RegistrationDAO.getRegistrationByPk(registration.getPk()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(registration);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return registration;
    }
}
