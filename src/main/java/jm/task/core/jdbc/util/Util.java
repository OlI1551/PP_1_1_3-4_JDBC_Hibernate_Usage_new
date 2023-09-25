package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import jm.task.core.jdbc.model.User;


public class Util {
    private static final String URL_DB = "jdbc:mysql://localhost:3306/jdbcdbusers";
    private static final String USER_NAME_DB = "DB_User1";
    private static final String PASSWORD_DB = "Abc12345@@@!";

    private static Connection connection = null;

    private static SessionFactory sessionFactory = null;


    public Util() {
    }

    public static Connection getConnection() {

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL_DB, USER_NAME_DB, PASSWORD_DB);

        } catch (SQLException e) {
            System.out.println("Не удалось загрузить класс драйвера!");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }


    public static SessionFactory getSessionFactory() {//

        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                // <property name="hibernate.bytecode.use_reflection_optimizer">false</property> - why is it omitted?

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, URL_DB);
                settings.put(Environment.USER, USER_NAME_DB);
                settings.put(Environment.PASS, PASSWORD_DB);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread"); // what for is it added?

                settings.put(Environment.HBM2DDL_AUTO, "update"); // create-drop would be better?

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.err.println("Initial SessionFactory creation failed.");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() throws SQLException {
        if (!sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

}
