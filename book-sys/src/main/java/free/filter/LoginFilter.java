package free.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/")
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getServletPath();
        if(!"/login.html".equals(uri) && !uri.startsWith("/public/")
                && !uri.startsWith("/static/") && !"/user/login".equals(uri)){


            HttpSession session = req.getSession(false);
            if(session == null){
                String schema = req.getScheme();
                String host = req.getServerName();
                int port = req.getServerPort();
                String contextPath = req.getContextPath();
                String basePath = schema+"://"+host+":"+port+contextPath;
                ((HttpServletResponse) response).sendRedirect(basePath+"/public/page/index.html");
                return;
            }

        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
