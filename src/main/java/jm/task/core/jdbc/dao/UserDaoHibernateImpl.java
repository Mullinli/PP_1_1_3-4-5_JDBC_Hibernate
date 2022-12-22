package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final Util util = new Util();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS " +
                            "USERS (ID BIGINT NOT NULL AUTO_INCREMENT, " +
                            "NAME VARCHAR(45) NOT NULL, " +
                            "LASTNAME VARCHAR(45) NOT NULL, " +
                            "AGE INT NULL, PRIMARY KEY (ID))")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Creating table error");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS USERS").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Drop table error");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = util.getSessionFactory().getCurrentSession()) {

            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Save model error");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.remove(session.get(User.class, id));
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Remove by id error");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Get all models error");
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Clean models error");
        }
    }
}
