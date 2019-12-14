//信管182 徐学印 201802104067
package controller;

import Service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import domain.User;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login.ctl")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得json数据
        String user_json= JSONUtil.getJSON(request);
        //将json对象转化为User对象
        User user = JSON.parseObject(user_json,User.class);
        JSONObject message = new JSONObject();
        try {
            //调用login方法进行登录
            User loggedUser = UserService.getInstance().login(user.getUsername(), user.getPassword());
            if (loggedUser != null) {
                //从请求中获取session如果没有则自动创建
                HttpSession session = request.getSession();
                //十分钟没有操作，则session失效
                session.setMaxInactiveInterval(10 * 60);
                //向session中添加
                session.setAttribute("currentUser", loggedUser);
                //将json数据转化为对象
                String teacher_json = JSON.toJSONString(loggedUser.getTeacher());
                //响应到前台
                response.getWriter().println(teacher_json);
                //此处应重定向到索引页（菜单页）
            } else {
                message.put("message", "用户名或密码不正确");
                //响应到前台
                response.getWriter().println(message);
            }
        }catch (SQLException e) {
            message.put("message", "数据库操作异常");
            //响应到前台
            e.printStackTrace();
        }catch (Exception e){
            message.put("message", "网络异常");
            //响应到前台
            e.printStackTrace();
        }
    }
}
