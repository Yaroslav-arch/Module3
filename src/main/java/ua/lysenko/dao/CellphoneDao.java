package ua.lysenko.dao;

import org.hibernate.SQLQuery;
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

    public String getTopPopularCellphoneModel() {

        Transaction transaction = null;
        List<Cellphone> results;
        String model = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "select c from Cellphone c group by c.id, c.model order by count(*) desc";
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

