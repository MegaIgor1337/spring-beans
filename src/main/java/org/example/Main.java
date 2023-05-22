package org.example;

import org.example.connection.ConnectionDataBase;
import org.example.service.CompanyService;
import org.example.service.UserService;
import org.example.util.HibernateUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("application.xml");
            var sessionFactory = HibernateUtil.buildSessionFactory(
                    context.getBean("connectionDataBase", ConnectionDataBase.class)
            );
            var session = sessionFactory.openSession()) {
            var userService = context.getBean("userService", UserService.class);
            System.out.println(userService.findById(session, 1L));
            var companyService = context.getBean("companyService", CompanyService.class);
            System.out.println(companyService.findById(session, 2));
        }
    }

}