package org.example.connection;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


@Data
public class ConnectionDataBase {
    private  String name;
    private  String password;
    private  String url;
    private  String driver;
    private  String poolSize;
    private  BlockingQueue<Connection> pool;

    public ConnectionDataBase(String name, String password, String url, String driver, String poolSize) {
        this.name = name;
        this.password = password;
        this.url = url;
        this.driver = driver;
        this.poolSize = poolSize;
    }




    private  void loadDriver() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private  void initConnectionPool() {
        var size = poolSize == null ? 12 : Integer.parseInt(poolSize);
        pool = new ArrayBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            var connection = open();
            var proxyConnection = (Connection) Proxy.newProxyInstance(ConnectionDataBase.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args) -> method.getName().equals("close") ?
                            pool.add((Connection) proxy) : method.invoke(connection, args));
            pool.add(proxyConnection);

        }
    }

    public  Connection get() {
        try {
            loadDriver();
            initConnectionPool();
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private  Connection open() {
        try {
            return DriverManager.getConnection(
                    url,
                    name,
                    password
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
