//信管182 徐学印 201802104067
package controller;

import Service.GraduateProjectCategoryService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import domain.GraduateProjectCategory;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/graduateProjectCategory.ctl")
public class GraduateProjectCategoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取id
        String id_str = request.getParameter("id");
        JSONObject message = new JSONObject();
        try {
            //若id_str为空
            if (id_str == null) {
                //执行responseDegrees方法
                responseDegrees(response);
            } else {
                //将string类型的转化为int类型
                int id = Integer.parseInt(id_str);
                //执行responseDegree方法
                responseDegree(id, response);
            }
        }catch (SQLException e){
            message.put("message", "数据库操作异常");
            e.printStackTrace();
            //响应到前台
            response.getWriter().println(message);
        }catch(Exception e){
            message.put("message", "网络异常");
            e.printStackTrace();
            //响应到前台
            response.getWriter().println(message);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得json数据
        String category_json = JSONUtil.getJSON(request);
        //将json对象转化为GraduateProjectCategory对象
        GraduateProjectCategory graduateProjectCategory = JSON.parseObject(category_json, GraduateProjectCategory.class);
        JSONObject message = new JSONObject();
        try {
            //调用add方法进行添加
            GraduateProjectCategoryService.getInstance().add(graduateProjectCategory);
            message.put("message","增加成功");
            //响应到前台
            response.getWriter().println(message);
        } catch (SQLException e) {
            message.put("message","数据库操作异常");
            e.printStackTrace();
            //响应到前台
            response.getWriter().println(message);
        } catch (Exception e){
            message.put("message","网络异常");
            e.printStackTrace();
            //响应到前台
            response.getWriter().println(message);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获得id
        String id_str = request.getParameter("id");
        //将string类型的id转换为int类型
        int id = Integer.parseInt(id_str);
        JSONObject message = new JSONObject();
        try {
            //调用delete方法删除
            GraduateProjectCategoryService.getInstance().delete(id);
            message.put("message","删除成功");
            //响应到前台
            response.getWriter().println(message);
        } catch (SQLException e) {
            message.put("message", "数据库操作异常");
            e.printStackTrace();
            //响应到前台
            response.getWriter().println(message);
        } catch (Exception e){
            message.put("message", "网络异常");
            e.printStackTrace();
            //响应到前台
            response.getWriter().println(message);
        }
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获得json数据
        String category_json = JSONUtil.getJSON(request);
        //将json对象转化为GraduateProjectCategory对象
        GraduateProjectCategory categoryToUpdate = JSON.parseObject(category_json, GraduateProjectCategory.class);
        JSONObject message = new JSONObject();
        try {
            //调用update方法进行修改
            GraduateProjectCategoryService.getInstance().update(categoryToUpdate);
            message.put("message","修改成功");
            //响应到前台
            response.getWriter().println(message);
        } catch (SQLException e) {
            message.put("message", "数据库操作异常");
            e.printStackTrace();
            //响应到前台
            response.getWriter().println(message);
        } catch (Exception e){
            message.put("message", "网络异常");
            e.printStackTrace();
            //响应到前台
            response.getWriter().println(message);
        }
    }

    private void responseDegree(int id, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //创建对象调用find方法根据id查找
        GraduateProjectCategory projectCategory = GraduateProjectCategoryService.getInstance().find(id);
        //将json数据转化为对象
        String projectCategory_json = JSON.toJSONString(projectCategory);
        //响应到前台
        response.getWriter().println(projectCategory_json);
    }
    private void responseDegrees(HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //创建集合调用findAll方法查询所有
        Collection<GraduateProjectCategory> projectCategories = GraduateProjectCategoryService.getInstance().findAll();
        //将json数据转化为对象
        String projectCategories_json = JSON.toJSONString(projectCategories);
        //创建对象调用find方法
        response.getWriter().println(projectCategories_json);
    }
}
