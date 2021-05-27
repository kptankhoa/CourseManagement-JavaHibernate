package dao;

import model.Course;
import model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;

public class CourseDAO {
    public static ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from Course c left join fetch c.subject left join fetch c.shift left join fetch c.semester";
            Query query = session.createQuery(hql);
            courses = (ArrayList<Course>) query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return courses;
    }

    public static Course getCourseById(String courseId){
        Course res = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from Course c left join fetch c.subject left join fetch c.shift left join fetch c.semester where c.courseId = :courseId";
            Query query = session.createQuery(hql);
            query.setString("courseId", courseId);
            res = (Course) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return res;
    }

    public static Course addCourse(Course course){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (CourseDAO.getCourseById(course.getCourseId()) != null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return course;
    }

    public static Course updateCourse(Course course){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (CourseDAO.getCourseById(course.getCourseId()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return course;
    }

    public static Course deleteCourse(Course course){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (CourseDAO.getCourseById(course.getCourseId()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(course);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return course;
    }

}
