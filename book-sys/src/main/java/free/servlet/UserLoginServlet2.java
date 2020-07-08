package free.servlet;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/login2")
public class UserLoginServlet2 extends AbstractBaseServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req,resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");//针对请求体设置编码格式
//        resp.setCharacterEncoding("UTF-8");//针对响应体设置编码
//        resp.setContentType("text/html");//设置响应的数据格式：响应头content-type告诉浏览器怎么解析数据
//        HashMap json = new ObjectMapper().readValue(req.getInputStream(), HashMap.class);
//
//        System.out.println(json);
//        HashMap<String,Object> r = new HashMap<>();
//        r.put("success",true);
//        r.put("code",200);
//        PrintWriter pw = resp.getWriter();
//        pw.println("登录成功");
//        pw.flush();
////        String username = req.getParameter("username");
////        String password = req.getParameter("password");
//
//        }
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return null;
    }
}
