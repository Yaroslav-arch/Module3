package ua.lysenko.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lysenko.entity.Message;
import ua.lysenko.entity.User;
import ua.lysenko.utils.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class MessageDao {

    public void saveMessage(Message message) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(message);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Message getMessage(long id) {

        Transaction transaction = null;
        Message message = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            message = session.get(Message.class, id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return message;
    }

    public Message getMessageByContent(String content) {

        Transaction transaction = null;
        Message message = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = " FROM Message message WHERE message.content like :content ";
            Query query = session.createQuery(hql);
            query.setParameter("content", "%" + content + "%");
            List<Message> results = query.getResultList();
            if (results != null && !results.isEmpty()) {
                message = results.get(0);
            }
                transaction.commit();

            } catch(Exception e){
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return message;
        }

    public List<User> getTop5ActiveUsers() {

        Transaction transaction = null;
        List<User> results = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "SELECT u FROM User u where u.id in (SELECT m.cellphone.id FROM Message m GROUP BY m.cellphone order by count(*) DESC)";
            Query query = session.createQuery(hql).setMaxResults(5);

            results = query.getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return results;
    }

    public int getTableSize() {

        Transaction transaction = null;
        int totalMessages = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "SELECT count(id) FROM Message ";
            Query query = session.createQuery(hql);
            Object callRecords = query.getSingleResult();
            totalMessages = Integer.parseInt(String.valueOf(callRecords));

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return totalMessages;
    }

        public void updateMessage (Message message){
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.update(message);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }

        public void deleteMessage ( long id){

            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                Message message = session.get(Message.class, id);
                if (message != null) {
                    session.delete(message);
                    System.out.println("Message is deleted");
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

