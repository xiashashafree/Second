package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Channel;
import model.ChannelDao;
import model.User;
import util.ChatroomException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/channel")
public class ChannelServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().create();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    //获取频道列表
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Channel> channels = new ArrayList<>();

        try {
            //1.验证登录状态，如果未登录则不能查看
            HttpSession httpSession = req.getSession(false);
            if(httpSession == null){
                throw new ChatroomException("您还没有登录哦(#^.^#)");
            }
            User user = (User)httpSession.getAttribute("user");
            //2.查数据库
            ChannelDao channelDao = new ChannelDao();
            channels = channelDao.select();

        } catch (ChatroomException e) {
            e.printStackTrace();

        } finally {
            //3.把查询结果包装成响应内容
            String jsonString = gson.toJson(channels);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(jsonString);
        }


    }
}
