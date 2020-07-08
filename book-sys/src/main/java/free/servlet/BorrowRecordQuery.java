package free.servlet;


import free.dao.BorrowRecordDAO;
import free.model.BorrowRecord;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/borrowRecord/query")
public class BorrowRecordQuery extends AbstractBaseServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<BorrowRecord> records = BorrowRecordDAO.query();
        return records;
    }
}
