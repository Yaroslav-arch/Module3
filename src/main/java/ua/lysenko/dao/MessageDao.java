package ua.lysenko.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lysenko.entity.Message;
import ua.lysenko.entity.User;
import ua.lysenko.utils.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class MessageDao {
    private UserDao userDao = new UserDao();

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

    @SuppressWarnings("unchecked")
    public Message getMessageByContent(String content) {

        Transaction transaction = null;
        Message message = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = " FROM Message message WHERE message.content like :content ";
            Query query = session.createQuery(hql);
            query.setParameter("content", "%" + content + "%");
            List results = query.getResultList();
            if (results != null && !results.isEmpty()) {
                message = (Message) results.get(0);
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

