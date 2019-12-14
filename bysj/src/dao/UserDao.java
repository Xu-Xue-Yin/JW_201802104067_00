//信管182 徐学印 201802104067
package dao;

import Service.TeacherService;
import Service.UserService;
import domain.User;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public final class UserDao {
    private static UserDao userDao = new UserDao();
    private UserDao() {}
    public static UserDao getInstance() { return userDao; }

    public User login(String username, String password) throws SQLException {
        User user = null;
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "SELECT * FROM user where username = ? AND password = ?");
        //赋值
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        //执行预编译语句获得结果集
        ResultSet resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            //根据id查找
            user = UserService.getInstance().find(resultSet.getInt("id"));
        }
        //关闭资源
        JdbcHelper.close(pstmt, connection);
        //返回对象
        return user;
    }

    public Collection<User> findAll() throws SQLException {
        //创建集合
        Collection<User> users = new TreeSet<User>();
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建语句盒子
        Statement stmt = connection.createStatement();
        //执行语句获得结果集
        ResultSet resultSet = stmt.executeQuery("select * from user");
        while (resultSet.next()) {
            //创建对象加到集合
            User user = new User(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getDate("loginTime"),
                    TeacherService.getInstance().find(resultSet.getInt("teacher_id")));
            //添加
            users.add(user);
        }
        //关闭资源
        JdbcHelper.close(stmt, connection);
        //返回集合
        return users;
    }

    public User find(Integer id) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "SELECT * FROM user where id = ?");
        //赋值
        pstmt.setInt(1, id);
        //执行预编译语句获得结果集
        ResultSet resultSet = pstmt.executeQuery();
        User user = null;
        while (resultSet.next()) {
            //创建对象
            user = new User(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getDate("loginTime"),
                    TeacherService.getInstance().find(resultSet.getInt("teacher_id")));
        }
        //关闭资源
        JdbcHelper.close(pstmt, connection);
        //返回对象
        return user;
    }

    public User findByUsername(String username) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "SELECT * FROM user WHERE username = ?");
        //赋值
        pstmt.setString(1, username);
        //执行预编译语句获得结果集
        ResultSet resultSet = pstmt.executeQuery();
        User user = null;
        while (resultSet.next()) {
            //创建对象
            user = new User(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getDate("loginTime"),
                    TeacherService.getInstance().find(resultSet.getInt("teacher_id")));
        }
        //关闭资源
        JdbcHelper.close(pstmt, connection);
        //返回对象
        return user;
    }

    public void update(User user) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "UPDATE user SET username = ?,password = ?,loginTime = ? teacher_id = ? where id = ?");
        //赋值
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.setDate(3, (Date) user.getLoginTime());
        pstmt.setInt(4, user.getTeacher().getId());
        pstmt.setInt(5, user.getId());
        //执行预编译语句获得影响的行数
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("修改了" + affectedRowNum + "行记录");
        //关闭资源
        JdbcHelper.close(pstmt, connection);
    }

    public void add(User user, Connection connection) throws SQLException {
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "INSERT INTO user" +
                                "(username,password,loginTime,teacher_id) VALUES" +
                                " (?,?,?,?)");
        //赋值
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.setDate(3, (Date) user.getLoginTime());
        pstmt.setInt(4, user.getTeacher().getId());
        //执行预编译语句获得影响的行数
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("添加了" + affectedRowNum + "行记录");
        //关闭资源
        pstmt.close();
    }

    public boolean delete(Integer id, Connection connection) throws SQLException {
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "delete from user where teacher_id=?");
        //赋值
        pstmt.setInt(1, id);
        //执行预编译语句获得影响的行数
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("删除了" + affectedRowNum + "行记录");
        //关闭资源
        pstmt.close();
        //返回true或false
        return affectedRowNum > 0;
    }

    public void delete(User user) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "DELETE FROM user WHERE id = ?");
        //赋值
        pstmt.setInt(1, user.getId());
        //执行预编译语句获得影响的行数
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("删除了" + affectedRowNum + "行记录");
        //关闭资源
        JdbcHelper.close(pstmt, connection);
    }
}
