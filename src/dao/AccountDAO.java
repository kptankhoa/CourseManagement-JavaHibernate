package dao;

import model.Account;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;

public class AccountDAO {
    public static ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "from Account";
            Query query = session.createQuery(hql);
            accounts = (ArrayList<Account>) query.list();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return accounts;
    }

    public static Account getAccountByUsername(String username) {
        Account acc = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select a from Account a where a.username = :username";
            Query query = session.createQuery(hql);
            query.setString("username", username);
            acc = (Account) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return acc;
    }

    public static Account addAccount(Account acc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (AccountDAO.getAccountByUsername(acc.getUsername()) != null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(acc);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return acc;
    }

    public static Account updateAccount(Account acc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (AccountDAO.getAccountByUsername(acc.getUsername()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(acc);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return acc;
    }

    public static Account deleteAccount(Account acc){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (AccountDAO.getAccountByUsername(acc.getUsername()) == null) {
            return null;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(acc);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return acc;
    }

}
