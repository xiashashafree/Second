package free.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/login")
public class UserLoginServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return null;
    }
}
