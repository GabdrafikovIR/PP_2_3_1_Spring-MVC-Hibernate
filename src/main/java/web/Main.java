package web;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import web.config.AppConfig;
import web.dao.UsersDao;
import web.model.User;
import web.service.UsersService;
import web.service.UsersServiceImp;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UsersService userService = context.getBean(UsersService.class);

        userService.save(new User("Ildar", "Gabdrafikov"));

    }
}
