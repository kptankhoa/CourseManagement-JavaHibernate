package dao;

import model.Course;
import model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;

public class CourseDAO {
    public static ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from Course";
            Query query = session.createQuery(hql);
            courses = (ArrayList<Course>) query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return courses;
    }
}
