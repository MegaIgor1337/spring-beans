package org.example.util;

import org.example.connection.ConnectionDataBase;
import org.example.convertor.BirthdayConvertor;
import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtil {
    public static SessionFactory buildSessionFactory(ConnectionDataBase connectionDataBase) {
        Configuration configuration = new Configuration();

        Properties properties = new Properties();
        properties.setProperty(Environment.URL, connectionDataBase.getUrl());
        properties.setProperty(Environment.USER, connectionDataBase.getName());
        properties.setProperty(Environment.PASS, connectionDataBase.getPassword());

        configuration.setProperty(Environment.DRIVER, connectionDataBase.getDriver());

        configuration.setProperties(properties);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(UserChat.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Profile.class);
        configuration.addAnnotatedClass(Chat.class);

        return configuration.buildSessionFactory();
    }
}
