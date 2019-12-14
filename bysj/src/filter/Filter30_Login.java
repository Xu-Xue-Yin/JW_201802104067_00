//信管182 徐学印 201802104067
package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Filter 3", urlPatterns = {"/*"})
public class Filter30_Login implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter 3 - log begins");
        //servletRequest强制类型转换为HttpServletRequest类型
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        //servletResponse强制类型转换为HttpServletResponse类型
        HttpServletResponse httpServletResponse =(HttpServletResponse)response;
        //如果当前请求对应着服务器内存中的一个session对象，则返回该对象
        //如果服务器内存没有session对象与当前请求对应，则返回null
        HttpSession session = httpServletRequest.getSession(false);
        //获得请求的路径
        String path= httpServletRequest.getRequestURI();
        if (path.contains("/login.ctl") || path.contains("/logout.ctl") || path.contains("/index.html")
                ||path.contains("/getSession") || path.contains("/createSession") ||
                path.contains("/getCookies")|| path.contains("/myapp")){
            //执行下一个过滤器，若其他过滤器执行完毕则执行原请求
            filterChain.doFilter(request,response);
            System.out.println("Filter 3 - log ends");
        }else if(session != null && session.getAttribute("currentUser") != null) {
            //执行下一个过滤器，若其他过滤器执行完毕则执行原请求
            filterChain.doFilter(request,response);
            System.out.println("Filter 3 - log ends");
        } else {
            response.getWriter().println("请先进行登录");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

}
