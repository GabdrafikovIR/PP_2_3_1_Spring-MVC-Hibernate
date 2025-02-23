package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UsersDao {

   @PersistenceContext
   private final EntityManager em;

   @Autowired
   public UserDaoImp(EntityManager em) {
      this.em = em;
   }


   @Override
   public void save(User user) {
      em.getTransaction().begin();
      em.persist(user);
      em.getTransaction().commit();
   }

   @Override
   public List<User> index() {
      TypedQuery<User> users = em.createQuery("from User", User.class);
      return users.getResultList();
   }

   @Override
   public void update(int id, User user) {
      em.getTransaction().begin();
      em.merge(user);
      em.getTransaction().commit();
   }

   @Override
   public void delete(int id) {
      em.getTransaction().begin();
      User user = em.find(User.class, id);
      em.remove(user);
      em.getTransaction().commit();
   }

   @Override
   public User show(int id) {
      em.getTransaction().begin();
      User user = em.find(User.class, id);
      em.getTransaction().commit();
      return user;
   }
}
