//信管182 徐学印 201802104067
package controller;

import Service.GraduateProjectService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import domain.GraduateProject;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/graduateProject.ctl")
public class GraduateProjectController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取json数据
        String project_json = JSONUtil.getJSON(request);
        //将json对象转化为GraduateProject对象
        GraduateProject graduateProject = JSON.parseObject(project_json, GraduateProject.class);
        JSONObject message = new JSONObject();
        try {
            //调用add方法添加
            GraduateProjectService.getInstance().add(graduateProject);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取id
        String id_str = request.getParameter("id");
        String teacherId_str = request.getParameter("teacherId");
        JSONObject message = new JSONObject();
        try {
            //判断id是否为空
            if (teacherId_str != null || id_str != null){
                if (teacherId_str != null){
                    //将string类型的id转化为int类型
                    int teacherID = Integer.parseInt(teacherId_str);
                    //创建集合调用findAllByTeacher方法进行查找
                    Collection<GraduateProject> graduateProjects = GraduateProjectService.getInstance().findAllByTeacher(teacherID);
                    //将json数据转换为对象
                    String graduateProject_json = JSON.toJSONString(graduateProjects);
                    //响应到前台
                    response.getWriter().println(graduateProject_json);
                } else if (id_str != null){
                    int id = Integer.parseInt(id_str);
                    //创建对象调用find方法进行查找
                    GraduateProject graduateProject = GraduateProjectService.getInstance().find(id);
                    //将json数据转化为对象
                    String project_json = JSON.toJSONString(graduateProject);
                    //响应到前台
                    response.getWriter().println(project_json);
                }
            }else {
                System.out.println("请输入ID");
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
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取id
        String id_str = request.getParameter("id");
        //将string类型转换为int类型
        int id = Integer.parseInt(id_str);
        JSONObject message = new JSONObject();
        try {
            //调用delete方法删除
            GraduateProjectService.getInstance().delete(id);
            message.put("message","删除成功");
            //响应到前台
            response.getWriter().println(message);
        } catch (SQLException e) {
            message.put("message", "数据库操作异常");
            e.printStackTrace();
            //响应到前台
            response.getWriter().println(message);
        } catch (Exception e) {
            message.put("message", "网络异常");
            e.printStackTrace();
            //响应到前台
            response.getWriter().println(message);
        }
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获得json数据
        String project_json = JSONUtil.getJSON(request);
        //将json数据转换为对象
        GraduateProject projectToAdd = JSON.parseObject(project_json, GraduateProject.class);
        JSONObject message = new JSONObject();
        try {
            //调用update方法进行修改
            GraduateProjectService.getInstance().update(projectToAdd);
            message.put("message", "修改成功");
            //响应到前台
            response.getWriter().println(message);
        } catch (SQLException e) {
            message.put("message", "数据库操作异常");
            e.printStackTrace();
            //响应到前台
            response.getWriter().println(message);
        } catch (Exception e) {
            message.put("message", "网络异常");
            e.printStackTrace();
            //响应到前台
            response.getWriter().println(message);
        }
    }
}
