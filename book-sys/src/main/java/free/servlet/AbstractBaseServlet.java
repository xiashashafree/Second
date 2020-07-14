package free.servlet;

import free.excpetion.BaseException;
import free.model.ResponseResult;
import free.util.CountHolder;
import free.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractBaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ResponseResult r = new ResponseResult();

        try {
            Object data=process(req,resp);
            r.setSuccess(true);
            r.setCode("000000");
            r.setMessage("操作成功");
            r.setTotal(CountHolder.get());
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
            //设置堆栈信息
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            r.setStackTrace(sw.toString());
        }finally{
            CountHolder.remove();
        }
        PrintWriter pw = resp.getWriter();
        pw.println(JSONUtil.write(r));
        pw.flush();
    }
    public abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
