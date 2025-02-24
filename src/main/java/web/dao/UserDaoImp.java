package web.dao;


import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UsersDao {

   @PersistenceContext
   private EntityManager entityManager;

   // Метод `save` аннотируем `@Transactional`
   @Override
   public void save(User user) {
      entityManager.persist(user);
   }

   // Метод `index` аннотируем `@Transactional`
   @Override
   public List<User> index() {
      TypedQuery<User> users = entityManager.createQuery("from User", User.class);
      return users.getResultList();
   }

   // Метод `update` аннотируем `@Transactional`
   @Override
   public void update(int id, User user) {
      entityManager.merge(user);
   }

   // Метод `delete` аннотируем `@Transactional`
   @Override
   public void delete(int id) {
      User user = entityManager.find(User.class, id);
      if (user != null) {
         entityManager.remove(user);
      }
   }

   // Метод `show` аннотируем `@Transactional`
   @Override
   public User show(int id) {
      return entityManager.find(User.class, id);
   }
}
