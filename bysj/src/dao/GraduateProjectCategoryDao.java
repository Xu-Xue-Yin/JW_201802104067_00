//信管182 徐学印 201802104067
package dao;

import domain.GraduateProjectCategory;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public final class GraduateProjectCategoryDao {
    private static GraduateProjectCategoryDao graduateProjectCategoryDao = new GraduateProjectCategoryDao();
    private GraduateProjectCategoryDao() {}
    public static GraduateProjectCategoryDao getInstance() {return graduateProjectCategoryDao;}

    public Collection<GraduateProjectCategory> findAll() throws SQLException {
        //创建集合
        Collection<GraduateProjectCategory> graduateProjectCategories = new TreeSet<GraduateProjectCategory>();
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建语句盒子
        Statement stmt = connection.createStatement();
        //创建结果集执行语句
        ResultSet resultSet = stmt.executeQuery("select * from projectcategory");
        while (resultSet.next()) {
            //创建对象加到集合中
            GraduateProjectCategory graduateProjectCategory = new GraduateProjectCategory(
                    resultSet.getInt("id"),
                    resultSet.getString("description"),
                    resultSet.getString("no"),
                    resultSet.getString("remarks"));
            //添加
            graduateProjectCategories.add(graduateProjectCategory);
        }
        //关闭资源
        JdbcHelper.close(stmt, connection);
        //返回集合
        return graduateProjectCategories;
    }

    public GraduateProjectCategory find(Integer id) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "SELECT * FROM projectcategory where id = ?");
        //赋值第一个参数为id
        pstmt.setInt(1, id);
        //执行预编译语句获取结果集
        ResultSet resultSet = pstmt.executeQuery();
        GraduateProjectCategory graduateProjectCategory = null;
        while (resultSet.next()) {
            //创建对象
            graduateProjectCategory = new GraduateProjectCategory(resultSet.getInt("id"),
                    resultSet.getString("description"),
                    resultSet.getString("no"),
                    resultSet.getString("remarks"));
        }
        //关闭资源
        JdbcHelper.close(pstmt, connection);
        //返回对象
        return graduateProjectCategory;
    }

    public void update(GraduateProjectCategory graduateProjectCategory) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "UPDATE projectcategory SET description = ?,no = ?,remarks = ? where id = ?");
        //赋值
        pstmt.setString(1, graduateProjectCategory.getDescription());
        pstmt.setString(2, graduateProjectCategory.getNo());
        pstmt.setString(3, graduateProjectCategory.getRemarks());
        pstmt.setInt(4, graduateProjectCategory.getId());
        //执行预编译语句获得改动的行数
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("修改了" + affectedRowNum + "行记录");
        //关闭资源
        JdbcHelper.close(pstmt, connection);
    }

    public void add(GraduateProjectCategory graduateProjectCategory) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "INSERT INTO projectcategory" +
                                " (no,description,remarks) VALUES" +
                                " (?,?,?)");
        //赋值
        pstmt.setString(1, graduateProjectCategory.getNo());
        pstmt.setString(2, graduateProjectCategory.getDescription());
        pstmt.setString(3, graduateProjectCategory.getRemarks());
        //执行预编译语句获得改动的行数
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("添加了" + affectedRowNum + "行记录");
        //关闭资源
        JdbcHelper.close(pstmt, connection);
    }

    public void delete(GraduateProjectCategory graduateProjectCategory) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "DELETE FROM projectcategory WHERE id = ?");
        //第一个参数赋值
        pstmt.setInt(1, graduateProjectCategory.getId());
        //执行预编译语句获得改动的行数
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("删除了" + affectedRowNum + "行记录");
        //关闭资源
        JdbcHelper.close(pstmt, connection);
    }

    public void delete(Integer id) throws SQLException {
        //根据id进行删除
        GraduateProjectCategory graduateProjectCategory = this.find(id);
        this.delete(graduateProjectCategory);
    }
}
