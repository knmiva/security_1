package ru.jm.springmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.jm.springmvc.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory factory;

    @Autowired
    public UserDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = factory.getCurrentSession();
        List<User> allUsers = session.createQuery("from User").list();
        return allUsers;
    }

    @Override
    public User getUserById(Long id) {
        Session session = factory.getCurrentSession();
        return (User) session.load(User.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        List<User> users;
        users = factory.getCurrentSession().createQuery("from User where username=?").setParameter(0, username)
                .list();
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        Session session = factory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        Session session = factory.getCurrentSession();
        User user = (User) session.load(User.class, id);
        if (user != null) {
            session.delete(user);
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = factory.getCurrentSession();
        session.update(user);
    }
}
