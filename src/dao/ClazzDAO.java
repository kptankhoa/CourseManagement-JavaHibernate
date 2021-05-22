package dao;

import model.Clazz;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;

public class ClazzDAO {
    public static ArrayList<Clazz> getAllClass() {
        ArrayList<Clazz> classes = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from Clazz";
            Query query = session.createQuery(hql);
            classes = (ArrayList<Clazz>) query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return classes;
    }

    public static Clazz getClassById(String classId){
        Clazz res = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select c from Clazz c where c.classId = :classId";
            Query query = session.createQuery(hql);
            query.setString("classId", classId);
            res = (Clazz) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return res;
    }

    public static Clazz addCLass(Clazz clazz) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (ClazzDAO.getClassById(clazz.getClassId()) != null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(clazz);
            transaction.commit();
        } catch (HibernateException ex) { //Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return clazz;
    }

    public static Clazz updateClass(Clazz clazz) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (ClazzDAO.getClassById(clazz.getClassId()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(clazz);
            transaction.commit();
        } catch (HibernateException ex) { //Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return clazz;
    }

    public static Clazz deleteClass(Clazz clazz) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (ClazzDAO.getClassById(clazz.getClassId()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(clazz);
            transaction.commit();
        } catch (HibernateException ex) { //Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return clazz;
    }
}
