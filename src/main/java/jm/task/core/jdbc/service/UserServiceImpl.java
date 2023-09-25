package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
//    private final UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    private final UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

    @Override
//    public void createUsersTable() {
//        userDaoJDBC.createUsersTable();
//    }
    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
    }

    @Override
//    public void dropUsersTable() {
//        userDaoJDBC.dropUsersTable();
//    }
    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
    }

    @Override
//    public void saveUser(String name, String lastName, byte age) {
//        userDaoJDBC.saveUser(name, lastName, age);
//    }
    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age);
    }

    @Override
//    public void removeUserById(long id) {
//        userDaoJDBC.removeUserById(id);
//    }
    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
    }

    @Override
//    public List<User> getAllUsers() {
//        return userDaoJDBC.getAllUsers();
//    }
    public List<User> getAllUsers() {
        return userDaoHibernate.getAllUsers();
    }

    @Override
//    public void cleanUsersTable() {
//        userDaoJDBC.cleanUsersTable();
//    }
    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
    }
}
