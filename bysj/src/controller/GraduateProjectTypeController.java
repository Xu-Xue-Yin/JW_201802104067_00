//信管182 徐学印 201802104067
package controller;

import Service.GraduateProjectTypeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import domain.GraduateProjectType;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/graduateProjectType.ctl")
public class GraduateProjectTypeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取id
        String id_str = request.getParameter("id");
        JSONObject message = new JSONObject();
        try {
            //若id_str为空
            if (id_str == null) {
                //执行responseTypes方法
                responseTypes(response);
            } else {
                //将id_str转化为int类型
                int id = Integer.parseInt(id_str);
                //执行responseType方法
                responseType(id, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获得json数据
        String type_json = JSONUtil.getJSON(request);
        //将json对象转化为GraduateProjectType对象
        GraduateProjectType graduateProjectType = JSON.parseObject(type_json, GraduateProjectType.class);
        JSONObject message = new JSONObject();
        try {
            //调用add方法进行添加
            GraduateProjectTypeService.getInstance().add(graduateProjectType);
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
        //获取id
        String id_str = request.getParameter("id");
        //id_str转化为int类型
        int id = Integer.parseInt(id_str);
        JSONObject message = new JSONObject();
        try {
            //调用delete方法根据id进行删除
            GraduateProjectTypeService.getInstance().delete(id);
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
        String type_json = JSONUtil.getJSON(request);
        //将json对象转化为GraduateProjectType对象
        GraduateProjectType typeToUpdate = JSON.parseObject(type_json, GraduateProjectType.class);
        JSONObject message = new JSONObject();
        try {
            //调用update方法进行修改
            GraduateProjectTypeService.getInstance().update(typeToUpdate);
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
    private void responseType(int id, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //创建对象调用find方法进行查找
        GraduateProjectType projectType = GraduateProjectTypeService.getInstance().find(id);
        //将json数据转化为对象
        String projectType_json = JSON.toJSONString(projectType);
        //响应到前台
        response.getWriter().println(projectType_json);
    }
    private void responseTypes(HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //创建集合调用findAll方法查询所有
        Collection<GraduateProjectType> projectCategories = GraduateProjectTypeService.getInstance().findAll();
        //将json数据转化为对象
        String projectTypes_json = JSON.toJSONString(projectCategories);
        //响应到前台
        response.getWriter().println(projectTypes_json);
    }
}
