package free.servlet;


import free.dao.BorrowRecordDAO;
import free.model.BorrowRecord;
import free.model.Page;
import free.util.Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/borrowRecord/query")
public class BorrowRecordQueryServlet extends AbstractBaseServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Page p = Util.parse(req);
        List<BorrowRecord> records = BorrowRecordDAO.query(p);
        return records;
    }
}
