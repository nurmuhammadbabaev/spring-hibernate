package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<?> getByModel(String model) {
      List<User>list=sessionFactory.openSession().createQuery("from User").list();
      list.stream().filter(user -> user.getCar().getModel().equals(model)).forEach(System.out::println);
      return list;
   }

   @Override
   public List<?> getBySeries(int series) {
      Session session=sessionFactory.openSession();
      session.beginTransaction();
     List<User>list=session.createQuery("from User ").list();
     list.stream().filter(user -> user.getCar().getSeries()==series).forEach(System.out::println);
     session.getTransaction().commit();
     session.close();
      return list;
   }


}
