package org.example;

import org.example.config.ApplicationConfig;
import org.example.connection.ConnectionDataBase;
import org.example.service.CompanyService;
import org.example.service.UserService;
import org.example.util.HibernateUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
             var sessionFactory = HibernateUtil.buildSessionFactory(
                     context.getBean(ConnectionDataBase.class)
             );
             var session = sessionFactory.openSession()) {
            var userService = context.getBean(UserService.class);
            System.out.println(userService.findById(session, 1L));
            var companyService = context.getBean(CompanyService.class);
            System.out.println(companyService.findById(session, 2));
        }
    }

}