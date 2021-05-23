package dao;

import model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;

public class StudentDAO {
    public static ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from Student s left join fetch s.clazz";
            Query query = session.createQuery(hql);
            students = (ArrayList<Student>) query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return students;
    }

    public static Student getStudentById(String studentId) {
        Student res = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select s from Student s left join fetch s.clazz where s.studentId = :studentId";
            Query query = session.createQuery(hql);
            query.setString("studentId", studentId);
            res = (Student) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return res;
    }

    public static Student addStudent(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (StudentDAO.getStudentById(student.getStudentId()) != null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return student;
    }

    public static Student updateStudent(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (StudentDAO.getStudentById(student.getStudentId()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return student;
    }

    public static Student deleteStudent(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (StudentDAO.getStudentById(student.getStudentId()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return student;
    }

}
