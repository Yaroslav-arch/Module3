package ua.lysenko.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lysenko.entity.Cellphone;
import ua.lysenko.entity.User;
import ua.lysenko.utils.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class CellphoneDao {

    public void saveCellphone(Cellphone cellphone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(cellphone);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Cellphone getCellphoneById(long id) {

        Transaction transaction = null;
        Cellphone cellphone = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            cellphone = session.get(Cellphone.class, id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return cellphone;
    }

    public List<User> getTopCallsCellphone() {

        Transaction transaction = null;
        List<User> users = null;
        List<Cellphone> results = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = " FROM Cellphone c ORDER BY size(c.calls) DESC";
            Query query = session.createQuery(hql).setMaxResults(5);

            results = (List<Cellphone>) query.getResultList();
            users = results.stream()
                    .map(Cellphone::getUser_id)
                    .toList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getTopSMSsCellphone() {

        Transaction transaction = null;
        List<Cellphone> results = null;
        List<User> users = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = " FROM Cellphone c ORDER BY size(c.calls) DESC";
            Query query = session.createQuery(hql).setMaxResults(5);

            results = (List<Cellphone>) query.getResultList();
            users = results.stream()
                    .map(Cellphone::getUser_id)
                    .toList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getTopWebActiveCellphone() {

        Transaction transaction = null;
        List<Cellphone> results = null;
        List<User> users = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = " FROM Cellphone c ORDER BY size(c.webSessions) DESC";
            Query query = session.createQuery(hql).setMaxResults(5);

            results = (List<Cellphone>) query.getResultList();
            users = results.stream()
                    .map(Cellphone::getUser_id)
                    .toList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }

    public String getTopPopularCellphoneModel() {

        Transaction transaction = null;
        List<Cellphone> results = null;
        String model = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

//            String hql = "SELECT count(c.model) FROM Cellphone c GROUP BY c.model order by count(*) DESC";
            String hql = "SELECT model FROM Cellphone GROUP BY model order by count(*) DESC";
            Query query = session.createQuery(hql).setMaxResults(1);

            results = query.getResultList();
            model = results.get(0).getModel();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return model;
    }


    public void updateCellphone(Cellphone cellphone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(cellphone);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCellphone(long id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Cellphone cellphone = session.get(Cellphone.class, id);
            if (cellphone != null) {
                session.delete(cellphone);
                System.out.println("Cellphone is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

