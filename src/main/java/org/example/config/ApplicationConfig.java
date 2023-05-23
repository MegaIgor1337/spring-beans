package org.example.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.example.connection.ConnectionDataBase;
import org.example.service.CompanyService;
import org.example.service.UserService;
import org.example.repo.CompanyRepository;
import org.example.repo.UserRepository;


@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("org.example")
public class ApplicationConfig {
    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.driver}")
    private String dbDriver;

    @Value("${db.poolSize}")
    private String dbPoolSize;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Bean
    @Profile("dev")
    public ConnectionDataBase devConnectionDataBase(@Value("${db.username}") String dbUsername,
                                                    @Value("${db.password}") String dbPassword,
                                                    @Value("${db.url}") String dbUrl,
                                                    @Value("${db.driver}") String dbDriver,
                                                    @Value("${db.poolSize}") String dbPoolSize,
                                                    @Value("${hibernate.dialect}") String hibernateDialect) {
        return new ConnectionDataBase(dbUsername, dbPassword, dbUrl, dbDriver, dbPoolSize, hibernateDialect);
    }

    @Bean
    @Profile("prod")
    public ConnectionDataBase prodConnectionDataBase(@Value("${db.username}") String dbUsername,
                                                     @Value("${db.password}") String dbPassword,
                                                     @Value("${db.url}") String dbUrl,
                                                     @Value("${db.driver}") String dbDriver,
                                                     @Value("${db.poolSize}") String dbPoolSize,
                                                     @Value("${hibernate.dialect}") String hibernateDialect) {
        return new ConnectionDataBase(dbUsername, dbPassword, dbUrl, dbDriver, dbPoolSize, hibernateDialect);
    }

    @Bean
    public UserRepository userRepository(ConnectionDataBase connectionDataBase) {
        return new UserRepository(connectionDataBase);
    }

    @Bean
    public CompanyRepository companyRepository(ConnectionDataBase connectionDataBase) {
        return new CompanyRepository(connectionDataBase);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public CompanyService companyService(CompanyRepository companyRepository) {
        return new CompanyService(companyRepository);
    }
}
