//信管182 徐学印 201802104067
package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Filter 1",urlPatterns = {"/*"})
public class Filter10_Encoding implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("Filter 1 - encoding begins");
        //servletRequest强制类型转换为HttpServletRequest类型
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        //servletResponse强制类型转换为HttpServletResponse类型
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        //获取请求路径
        String path= request.getRequestURI();
        //获得请求方法
        String method=request.getMethod();
        if (path.contains(".html")||path.contains(".ico")||path.contains(".css")
                || path.contains(".js") || path.contains(".png") || path.contains(".jpg")){
            filterChain.doFilter(request, response);
            System.out.println("Filter 1 - encoding ends");
        }else {
            //请求设置响应编码格式为utf8
            response.setContentType("text/html;charset=UTF-8");
            //如果是添加和修改方法则设置请求编码格式
            if (method.equals("POST")||method.equals("PUT")){
                request.setCharacterEncoding("UTF-8");
            }
        }
        //执行下一个过滤器，若其他过滤器执行完毕则执行原请求
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Filter 1 - encoding ends");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
