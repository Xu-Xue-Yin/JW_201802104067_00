//信管182 徐学印 201802104067
package dao;

import domain.GraduateProjectType;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public final class GraduateProjectTypeDao {
    private static GraduateProjectTypeDao graduateProjectTypeDao = new GraduateProjectTypeDao();
    private GraduateProjectTypeDao() { }
    public static GraduateProjectTypeDao getInstance() {
        return graduateProjectTypeDao;
    }

    public Collection<GraduateProjectType> findAll() throws SQLException {
        //创建集合
        Collection<GraduateProjectType> graduateProjectTypes = new TreeSet<GraduateProjectType>();
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建语句盒子
        Statement stmt = connection.createStatement();
        //执行语句获得结果集
        ResultSet resultSet = stmt.executeQuery("select * from projecttype");
        while (resultSet.next()) {
            //创建对象加到集合中
            GraduateProjectType graduateProjectType = new GraduateProjectType(resultSet.getInt("id"),
                    resultSet.getString("description"),
                    resultSet.getString("no"),
                    resultSet.getString("remarks"));
            //添加
            graduateProjectTypes.add(graduateProjectType);
        }
        //关闭资源
        JdbcHelper.close(stmt, connection);
        //返回集合
        return graduateProjectTypes;
    }

    public GraduateProjectType find(Integer id) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "SELECT * FROM projecttype where id = ?");
        //赋值
        pstmt.setInt(1, id);
        //执行预编译语句获得结果集
        ResultSet resultSet = pstmt.executeQuery();
        GraduateProjectType graduateProjectType = null;
        while (resultSet.next()) {
            //创建对象
            graduateProjectType = new GraduateProjectType(resultSet.getInt("id"),
                    resultSet.getString("description"),
                    resultSet.getString("no"),
                    resultSet.getString("remarks"));
        }
        //关闭资源
        JdbcHelper.close(pstmt, connection);
        //返回对象
        return graduateProjectType;
    }

    public void update(GraduateProjectType graduateProjectType) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "UPDATE projecttype SET description = ?,no = ?,remarks = ? where id = ?");
        //赋值
        pstmt.setString(1, graduateProjectType.getDescription());
        pstmt.setString(2, graduateProjectType.getNo());
        pstmt.setString(3, graduateProjectType.getRemarks());
        pstmt.setInt(4, graduateProjectType.getId());
        //执行预编译获得影响的行数
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("修改了" + affectedRowNum + "行记录");
        //关闭资源
        JdbcHelper.close(pstmt, connection);
    }

    public void add(GraduateProjectType graduateProjectType) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO projecttype " +
                        "(no,description,remarks) VALUES" +
                        " (?,?,?)");
        //赋值
        pstmt.setString(1, graduateProjectType.getNo());
        pstmt.setString(2, graduateProjectType.getDescription());
        pstmt.setString(3, graduateProjectType.getRemarks());
        //执行预编译获得影响的行数
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("添加了" + affectedRowNum + "行记录");
        //关闭资源
        JdbcHelper.close(pstmt, connection);
    }

    public void delete(GraduateProjectType graduateProjectType) throws SQLException {
        //获得连接对象
        Connection connection = JdbcHelper.getConn();
        //创建预编译语句
        PreparedStatement pstmt =
                connection.prepareStatement(
                        "DELETE FROM projecttype WHERE id = ?");
        //赋值
        pstmt.setInt(1, graduateProjectType.getId());
        //执行预编译获得影响的行数
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("删除了 " + affectedRowNum + " 行记录");
        //关闭资源
        JdbcHelper.close(pstmt, connection);
    }

    public void delete(Integer id) throws SQLException {
        GraduateProjectType graduateProjectType = this.find(id);
        this.delete(graduateProjectType);
    }
}
