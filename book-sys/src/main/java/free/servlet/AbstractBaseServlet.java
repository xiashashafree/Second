package free.servlet;

import free.excpetion.BaseException;
import free.model.ResponseResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractBaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        ResponseResult r = null;

        try {
            Object data=process(req,resp);
            r.setSuccess(true);
            r.setCode("000000");
            r.setMessage("操作成功");
            r.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof BaseException){
                BaseException be = (BaseException) e;
                r.setCode(be.getCode());
                r.setMessage(be.getMessage());
            }else{
                r.setCode("500");
                r.setMessage("未知的错误");
            }
        }

    }
    public abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
