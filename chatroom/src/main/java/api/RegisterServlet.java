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
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private  Gson gson = new GsonBuilder().create();

    //这个类以内部类的方式实现，因为这个Request类知识针对Register来使用
    //其他的Servlet对应的Request类可能结构不同
    //从body的json中转换过来的
    static class Request{
        public String name;
        public String password;
        public String nickName;
    }
    //响应的数据内容
    //要把这个对象在转回为json字符串，写回给客户端
    static class Response{
        public int ok;
        public String reason;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Response response= new Response();
        //1.读取body中的信息json格式字符串
        try {
            String body = Util.readBody(req);
            //2.把json数据转成JAVA对象
            //  创建一个request来表示请求body
            //
            Request request = gson.fromJson(body, Request.class);
            //3.在数据库中查询，看是否存在此用户没如果存在就注册失败
            UserDao userDao = new UserDao();
            User existsUser = userDao.selectByName(request.name);
            if(existsUser != null){
                throw new ChatroomException("用户已存在");

            }
            User user = new User();
            user.setName(request.name);
            user.setPassword(request.password);
            user.setName(request.nickName);
            userDao.add(user);
            response.ok = 1;
            response.reason = "";
        } catch (JsonSyntaxException |ChatroomException e) {
            e.printStackTrace();
            response.ok = 0;
            response.reason = e.getMessage();
        }finally{
            resp.setContentType("application/json; charset=utf-8");
            String jsonString = gson.toJson(response);
            resp.getWriter().write(jsonString);
        }

    }
}
