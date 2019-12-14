//信管182 徐学印 201802104067
package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Calendar;

@WebFilter(filterName = "Filter 2",urlPatterns = {"/*"})
public class Filter20_Date implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("Filter 2 - date begins");
        //servletRequest强制类型转换为HttpServletRequest类型
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        //获得请求的路径
        String path=request.getRequestURI();
        //获得日期
        Calendar cal = Calendar.getInstance();
        String time= cal.get(Calendar.YEAR)+"年" +
                (cal.get(Calendar.MONTH) + 1)+ "月"+
                cal.get(Calendar.DATE)+"日"+
                cal.get(Calendar.HOUR_OF_DAY)+": "+
                cal.get(Calendar.MINUTE);
        System.out.println(path+" @ "+time);
        //执行下一个过滤器，若其他过滤器执行完毕则执行原请求
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Filter 2 - date ends");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
