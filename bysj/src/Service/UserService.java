//信管182 徐学印 201802104067
package Service;

import dao.UserDao;
import domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public final class UserService {
    private UserDao userDao = UserDao.getInstance();
    private static UserService userService = new UserService();

    public UserService() {
    }

    public static UserService getInstance() {
        return UserService.userService;
    }

    //查找所有对象的方法
    public Collection<User> findAll() throws SQLException {
        return userDao.findAll();
    }

    //根据id查找的方法
    public User find(Integer id) throws SQLException {
        return userDao.find(id);
    }

    //修改方法
    public void update(User user) throws SQLException {
        userDao.update(user);
    }

    //添加方法
    public void add(User user, Connection collection) throws SQLException {
        userDao.add(user, collection);
    }

    //删除方法
    public void delete(Integer id, Connection connection) throws SQLException {
        userDao.delete(id, connection);
    }

    //删除方法
    public void delete(User user) throws SQLException {
        userDao.delete(user);
    }

    //登录方法
    public User login(String username, String password) throws SQLException {
        return userDao.login(username, password);
    }
}
