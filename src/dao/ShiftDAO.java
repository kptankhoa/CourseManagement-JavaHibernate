package dao;

import model.Shift;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;

public class ShiftDAO {

    public static ArrayList<Shift> getAllShifts() {
        ArrayList<Shift> res = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from Shift";
            Query query = session.createQuery(hql);
            res = (ArrayList<Shift>) query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return res;
    }

    public static Shift getShiftById(int shiftId) {
        Shift res = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select s from Shift s where s.id=:shiftId";
            Query query = session.createQuery(hql);
            query.setString("shiftId", String.valueOf(shiftId));
            res = (Shift) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return res;
    }
}

