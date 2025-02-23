package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UsersDao;
import web.model.User;

import java.util.List;

@Service
public class UsersServiceImp implements UsersService {

   private final UsersDao usersDao;

   @Autowired
   public UsersServiceImp(UsersDao usersDao) {
      this.usersDao = usersDao;
   }

   @Transactional
   @Override
   public void save(web.model.User user) {
      usersDao.save(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<web.model.User> index() {
      return usersDao.index();
   }

   @Transactional(readOnly = true)
   @Override
   public web.model.User show(int id) {
      return usersDao.show(id);
   }

   @Transactional
   @Override
   public void update(int id, web.model.User updatedUser) {
      usersDao.update(id, updatedUser);
   }

   @Transactional
   @Override
   public void delete(int id) {
      usersDao.delete(id);
   }
}
