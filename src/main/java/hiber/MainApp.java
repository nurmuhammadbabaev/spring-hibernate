package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.xml.ws.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainApp {

   static Scanner scanner=new Scanner(System.in);
   static String modelName;
   static  int numberSeries;
   static int number;
   public static void main(String[] args) throws SQLException {

      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      System.out.println("Все данные");

      userService.add(new User("User1", "Lastname1", "user1@mail.ru",new Car("BMW",22)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru",new Car("Mercedes",23)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru",new Car("Jip",24)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",new Car("Audi",25)));
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      while (true) {
         System.out.println("Поисковик: 1 найти пользователья через MODEL, 2 найти пользователья через SERIES, 0 BREAK!");
         number = scanner.nextInt();
         if (number == 1) {
            System.out.println("Найдите пользователья через Model!");
            modelName = scanner.next();

            switch (modelName){
               case "BMW":
                  userService.getByModel("BMW");break;
               case "Mercedes":
                  userService.getByModel("Mercedes");break;
               case "Jip":
                  userService.getByModel("Jip");break;
               case "Audi":
                  userService.getByModel("Audi");break;
               default:
                  userService.getByModel("0");
            }
         } else if (number == 2) {
            System.out.println("Найдите пользователья через Series!");
            numberSeries = scanner.nextInt();
            switch (numberSeries){
               case 22:
                  userService.getBySeries(22);break;
               case 23:
                  userService.getBySeries(23);break;
               case 24:
                  userService.getBySeries(24);break;
               case 25:
                  userService.getBySeries(25);break;
               default:
                  userService.getBySeries(0);
            }

         } else if (number == 0) {
            System.err.println();
            break;

         } else {
            System.out.println("Нет такого поисковика!");
         }
      }



      context.close();
   }
}
