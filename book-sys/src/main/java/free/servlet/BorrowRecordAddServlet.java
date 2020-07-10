package free.servlet;

import free.dao.BorrowRecordDAO;
import free.excpetion.BusinessException;
import free.model.BorrowRecord;
import free.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/borrowRecord/add")
public class BorrowRecordAddServlet extends AbstractBaseServlet{

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        BorrowRecord record = JSONUtil.read(req.getInputStream(),BorrowRecord.class);
        int num = BorrowRecordDAO.insert(record);
        if(num != 1){
            throw new BusinessException("00008","插入图书信息数量异常");
        }
        return null;
    }
}
