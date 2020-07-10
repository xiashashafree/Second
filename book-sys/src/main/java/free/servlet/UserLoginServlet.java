package free.servlet;

import free.dao.UserDAO;
import free.excpetion.BusinessException;
import free.model.User;
import free.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user/login")
public class UserLoginServlet extends AbstractBaseServlet {


    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //req.getParameter("");这个方式只能获取url和请求体：k=V
        User user = JSONUtil.read(req.getInputStream(),User.class);//http请求解析的用户数据
        User queryUser = UserDAO.query(user);
        if(queryUser == null){
            throw new BusinessException("00001","用户名密码校验错误");
        }
        HttpSession session = req.getSession();
        session.setAttribute("user",queryUser);
        return queryUser;
    }
}
