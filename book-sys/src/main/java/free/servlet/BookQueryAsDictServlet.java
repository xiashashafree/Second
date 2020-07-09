package free.servlet;

import free.dao.BookDAO;
import free.model.Book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/book/queryAsDict")
public class BookQueryAsDictServlet extends AbstractBaseServlet{

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Book> books = new ArrayList<>();

        books = BookDAO.queryAsDict();
        return books;
    }
}
