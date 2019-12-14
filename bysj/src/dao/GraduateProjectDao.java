//信管182 徐学印 201802104067
package dao;

import Service.GraduateProjectCategoryService;
import Service.GraduateProjectTypeService;
import Service.TeacherService;
import domain.GraduateProject;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public final class GraduateProjectDao {
    private static GraduateProjectDao graduateProjectDao = new GraduateProjectDao();
    private GraduateProjectDao() { }
    public static GraduateProjectDao getInstance() {
        return graduateProjectDao;
    }

    public Collection<GraduateProject> findAll() throws SQLException {
        //创建集合
        Collection<GraduateProject> graduateProjects = new TreeSet<GraduateProject>();
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建语句盒子
        Statement stmt = connection.createStatement();
        //执行语句获得结果集
        ResultSet resultSet = stmt.executeQuery("select * from project");
        while (resultSet.next()) {
            //创建对象加到集合中
            GraduateProject graduateProject = new GraduateProject(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    GraduateProjectCategoryService.getInstance().find(resultSet.getInt("ProjectCategory_id")),
                    GraduateProjectTypeService.getInstance().find(resultSet.getInt("ProjectType_id")),
                    TeacherService.getInstance().find(resultSet.getInt("Teacher_id")));
            //添加
            graduateProjects.add(graduateProject);
        }
        //关闭资源
        JdbcHelper.close(stmt, connection);
        //返回集合
        return graduateProjects;
    }

    public Collection<GraduateProject> findAllByTeacher(int teacherID) throws SQLException {
        //创建集合
        Collection<GraduateProject> graduateProjects = new TreeSet<GraduateProject>();
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建语句盒子
        Statement stmt = connection.createStatement();
        //执行语句获得结果集
        ResultSet resultSet = stmt.executeQuery("select * from project where Teacher_id = " + teacherID);
        while (resultSet.next()) {
            //创建对象加到集合中
            GraduateProject graduateProject = new GraduateProject(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    GraduateProjectCategoryService.getInstance().find(resultSet.getInt("ProjectCategory_id")),
                    GraduateProjectTypeService.getInstance().find(resultSet.getInt("ProjectType_id")),
                    TeacherService.getInstance().find(resultSet.getInt("Teacher_id")));
            //添加
            graduateProjects.add(graduateProject);
        }
        //关闭资源
        JdbcHelper.close(stmt, connection);
        //返回集合
        return graduateProjects;
    }

    public GraduateProject find(Integer id) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "SELECT * FROM project where id = ?");
        //第一个参数赋值为id
        pstmt.setInt(1, id);
        //执行预编译语句获得结果集
        ResultSet resultSet = pstmt.executeQuery();
        GraduateProject graduateProject = null;
        while (resultSet.next()) {
            //创建对象
            graduateProject = new GraduateProject(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    GraduateProjectCategoryService.getInstance().find(resultSet.getInt("ProjectCategory_id")),
                    GraduateProjectTypeService.getInstance().find(resultSet.getInt("ProjectType_id")),
                    TeacherService.getInstance().find(resultSet.getInt("Teacher_id")));
        }
        //关闭资源
        JdbcHelper.close(pstmt, connection);
        //返回对象
        return graduateProject;
    }

    public void update(GraduateProject graduateProject) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "UPDATE project SET title=?,ProjectCategory_id = ?,ProjectType_id = ?,Teacher_id = ? where id = ?");
        //赋值
        pstmt.setString(1, graduateProject.getTitle());
        pstmt.setInt(2, graduateProject.getGraduateProjectCategory().getId());
        pstmt.setInt(3, graduateProject.getGraduateProjectType().getId());
        pstmt.setInt(4, graduateProject.getTeacher().getId());
        pstmt.setInt(5, graduateProject.getId());
        //执行预编译语句获得改动的行数
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("修改了 " + affectedRowNum + " 行记录");
        //关闭资源
        JdbcHelper.close(pstmt, connection);
    }

    public void add(GraduateProject graduateProject) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            //获得连接独享
            connection = JdbcHelper.getConn();
            //关闭自动提交
            connection.setAutoCommit(false);
            //创建预编译语句
            pstmt = connection.prepareStatement(
                    "INSERT INTO project(title,ProjectCategory_id,ProjectType_id,Teacher_id) " +
                            "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            //赋值
            pstmt.setString(1, graduateProject.getTitle());
            pstmt.setInt(2, graduateProject.getGraduateProjectCategory().getId());
            pstmt.setInt(3, graduateProject.getGraduateProjectType().getId());
            pstmt.setInt(4, graduateProject.getTeacher().getId());
            //执行预编译语句获得改动的行数
            int affectedRowNum = pstmt.executeUpdate();
            System.out.println("添加了" + affectedRowNum + "行记录");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\nerrorCode = " + e.getErrorCode());
            try {
                //若connection不为空
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
                    //回复自动提交
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //关闭资源
            JdbcHelper.close(pstmt, connection);
        }
    }

    public void delete(Integer id) throws SQLException {
        GraduateProject graduateProject = this.find(id);
        this.delete(graduateProject);
    }

    public void delete(GraduateProject graduateProject) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            //获得连接对象
            connection = JdbcHelper.getConn();
            //创建预编译语句
            pstmt = connection.prepareStatement(
                    "DELETE FROM project WHERE id = ?");
            pstmt.setInt(1, graduateProject.getId());
            //执行预编译语句获得影响的行数
            int affectedRowNum = pstmt.executeUpdate();
            System.out.println("删除了" + affectedRowNum + "行记录");
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
                    //回复自动提交
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //关闭资源
            JdbcHelper.close(pstmt, connection);
        }
    }
}
