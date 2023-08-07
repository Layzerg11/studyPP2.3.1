package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.entity.User;

import javax.persistence.*;
import java.util.List;

@Repository
@Component
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUserList() {
        List<User> users = entityManager.createQuery("from User", User.class).getResultList();
        return users;
    }

    @Override
    public User getUser(int id) {
        TypedQuery<User> query = entityManager.createQuery("from User u WHERE u.id =:id", User.class);
        query.setParameter("id", id);
        User user = query.getSingleResult();
        return user;
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(int id, User updateUser) {
        entityManager.merge(updateUser);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }
}
