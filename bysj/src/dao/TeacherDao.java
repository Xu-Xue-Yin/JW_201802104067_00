//信管182 徐学印 201802104067
package dao;

import Service.UserService;
import domain.Teacher;
import domain.User;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public final class TeacherDao {
    private static TeacherDao teacherDao = new TeacherDao();
    private TeacherDao() {}
    public static TeacherDao getInstance() {
        return teacherDao;
    }

    public Collection<Teacher> findAll() throws SQLException {
        //创建集合
        Collection<Teacher> teachers = new TreeSet<Teacher>();
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建语句盒子
        Statement stmt = connection.createStatement();
        //执行语句获得结果集
        ResultSet resultSet = stmt.executeQuery("select * from teacher");
        while (resultSet.next()) {
            //创建对象加到集合中
            Teacher teacher = new Teacher(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("no"));
            //添加
            teachers.add(teacher);
        }
        //关闭资源
        JdbcHelper.close(stmt, connection);
        //返回集合
        return teachers;
    }

    public Teacher find(Integer id) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "SELECT * FROM teacher where id = ?");
        //赋值
        pstmt.setInt(1, id);
        //执行预编译语句获得结果集
        ResultSet resultSet = pstmt.executeQuery();
        Teacher teacher = null;
        while (resultSet.next()) {
            //创建对象
            teacher = new Teacher(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("no"));
        }
        //关闭资源
        JdbcHelper.close(pstmt, connection);
        //返回对象
        return teacher;
    }

    public boolean update(Teacher teacher) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "UPDATE teacher SET name=?,no = ? where id = ?");
        //赋值
        pstmt.setString(1, teacher.getName());
        pstmt.setString(2, teacher.getNo());
        pstmt.setInt(3, teacher.getId());
        //执行预编译语句获得影响的行数
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("修改了" + affectedRowNum + "行数据");
        //关闭资源
        JdbcHelper.close(pstmt, connection);
        //返回true或false
        return affectedRowNum > 0;
    }

    public boolean add(Teacher teacher) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        boolean affectedRowNum = false;
        try {
            //获得连接对象
            connection = JdbcHelper.getConn();
            //关闭自动提交
            connection.setAutoCommit(false);
            //创建预编译语句
            pstmt = connection.prepareStatement(
                    "INSERT INTO teacher(name,no) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            //赋值
            pstmt.setString(1, teacher.getName());
            pstmt.setString(2, teacher.getNo());
            //执行预编译语句
            affectedRowNum = pstmt.executeUpdate() > 0;
            System.out.println("添加了" + affectedRowNum + "行记录");
            //执行getGeneratedKeys()方法获得结果集
            ResultSet resultSet = pstmt.getGeneratedKeys();
            resultSet.next();
            //赋值
            teacher.setId(resultSet.getInt(1));
            //创建java.util.Date对象
            java.util.Date date_util = new java.util.Date();
            //获得日期
            Long date_long = date_util.getTime();
            //创建Date对象
            Date date_sql = new Date(date_long);
            //执行user的add方法
            UserService.getInstance().add(new User(teacher.getNo(), teacher.getNo(), date_sql, teacher), connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\nerrorCode = " + e.getErrorCode());
            try {
                if (connection != null) {
                    //回滚当前事务
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    //恢复自动提交
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //关闭资源
            JdbcHelper.close(pstmt, connection);
        }
        //返回true或false
        return affectedRowNum;
    }

    public void delete(Integer id) throws SQLException {
        Teacher teacher = this.find(id);
        this.delete(teacher);
    }

    public boolean delete(Teacher teacher) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean affectedRowNum = false;
        try {
            //获得连接对象
            connection = JdbcHelper.getConn();
            //关闭自动提交
            connection.setAutoCommit(false);
            //执行user的delete方法
            UserService.getInstance().delete(teacher.getId(), connection);
            //创建预编译语句
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM Teacher WHERE id=?");
            //赋值
            preparedStatement.setInt(1, teacher.getId());
            affectedRowNum = preparedStatement.executeUpdate() > 0;
            //提交
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\nerrorcode=" + e.getErrorCode());
            try {
                if (connection != null) {
                    //回滚当前事务
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    //恢复自动提交
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //关闭资源
        JdbcHelper.close(preparedStatement, connection);
        //返回true或false
        return affectedRowNum;
    }
}
