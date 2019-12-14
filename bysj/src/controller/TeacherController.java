//信管182 徐学印 201802104067
package controller;

import Service.TeacherService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import domain.Teacher;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/teacher.ctl")
public class TeacherController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获得json数据
        String teacher_json = JSONUtil.getJSON(request);
        //将json对象转化为Teacher对象
        Teacher teacherToAdd = JSON.parseObject(teacher_json, Teacher.class);
        teacherToAdd.setId(4 + (int)(Math.random()*100));
        JSONObject message = new JSONObject();
        try {
            //调用add方法进行添加
            TeacherService.getInstance().add(teacherToAdd);
            message.put("message", "增加成功");
            //响应到前台
            response.getWriter().println(message);
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
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得id
        String id_str = request.getParameter("id");
        //string类型转化为int类型
        int id = Integer.parseInt(id_str);
        JSONObject message = new JSONObject();
        try {
            //调用delete根据id进行删除
            TeacherService.getInstance().delete(id);
            message.put("message", "删除成功");
            //响应到前台
            response.getWriter().println(message);
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
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获得json数据
        String teacher_json = JSONUtil.getJSON(request);
        //将json对象转化为Teacher对象
        Teacher teacherToAdd = JSON.parseObject(teacher_json, Teacher.class);
        JSONObject message = new JSONObject();
        try {
            //调用update方法进行修改
            TeacherService.getInstance().update(teacherToAdd);
            message.put("message", "修改成功");
            //响应到前台
            response.getWriter().println(message);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取id
        String id_str = request.getParameter("id");
        JSONObject message = new JSONObject();
        try {
            if (id_str == null) {
                //执行responseTeachers方法
                responseTeachers(response);
            } else {
                //string类型转化为int类型
                int id = Integer.parseInt(id_str);
                //执行responseTeacher方法
                responseTeacher(id, response);
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
    private void responseTeacher(int id, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //创建对象查找id
        Teacher teacher = TeacherService.getInstance().find(id);
        //将json数据转化为对象
        String teacher_json = JSON.toJSONString(teacher);
        //响应到前台
        response.getWriter().println(teacher_json);
    }
    private void responseTeachers(HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //创建集合获取所有对象
        Collection<Teacher> teachers = TeacherService.getInstance().findAll();
        //json数据转化为对象
        String teachers_json = JSON.toJSONString(teachers, SerializerFeature.DisableCircularReferenceDetect);
        //响应到前台
        response.getWriter().println(teachers_json);
    }
}
