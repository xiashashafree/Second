package api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import model.User;
import model.UserDao;
import util.ChatroomException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    static class Request{
        public String name;
        public String password;
    }
    static class Response{
        public int ok;
        public String reason;
        public int userId;
        public String name;
        public String nickName;


    }
    private  Gson gson = new GsonBuilder().create();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入doPost");
        req.setCharacterEncoding("utf-8");
        Response response = new Response();
        try {
            //1.读取body中数据
            String body = Util.readBody(req);

            //2.将读到的数据转成request
            Request request = gson.fromJson(body,Request.class);
            //3.按用户名查询密码，匹配密码正确性
            UserDao userDao = new UserDao();
            User user = userDao.selectByName(request.name);

            System.out.println(user.toString());
            //4.登录失败给出提示
            System.out.println(request.password+" "+user.getPassword());
            if(user == null || !request.password.equals(user.getPassword())){
                System.out.println("oooooooooooo");
                throw new ChatroomException("用户名或密码错误");
            }
            //5.登陆成功：创建session对象
            HttpSession session = req.getSession(true);
            session.setAttribute("user",user);
            //6.把结果写回到浏览器
            response.ok = 1;
            response.name = user.getName();
            response.userId = user.getUserId();
            response.nickName = user.getNickName();
            response.reason="";
        } catch (JsonSyntaxException | ChatroomException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.ok = 0;
            response.reason=e.getMessage();
        }finally {
            String jsonString = gson.toJson(response);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(jsonString);
        }

    }

    //检测登录状态
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            req.setCharacterEncoding("utf-8");
            Response response = new Response();
            try {
            //1.根据请求，查看该sessionID对应的session是否存在
            //  如果session不存在，就是登录是失败的状态
            HttpSession httpSession = req.getSession(false);
            if(httpSession == null){
                throw new ChatroomException("");
            }
            User user = (User)httpSession.getAttribute("user");
            response.ok = 1;
            response.reason = "";
            response.userId = user.getUserId();
            response.nickName = user.getNickName();
            response.name = user.getName();
            //2.如果session存在，直接返回一个登录唱功
        } catch (ChatroomException e) {
            e.printStackTrace();
            response.ok=0;
            response.reason=e.getMessage();
        }finally {
                resp.setContentType("application/json; charset=utf-8");
                String jsonString = gson.toJson(response);
                resp.getWriter().write(jsonString);
            }
    }
}
