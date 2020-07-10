package free.servlet;

import free.dao.BorrowRecordDAO;
import free.excpetion.BusinessException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/borrowRecord/delete")
public class BorrowRecordDeleteServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] ids = req.getParameterValues("ids");
        int num = BorrowRecordDAO.delete(ids);
        if(num != ids.length){
            throw new BusinessException("00009","删除图书信息数量异常");
        }
        return null;

    }
}
