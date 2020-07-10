package free.servlet;

import free.dao.StudentDAO;
import free.model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/student/queryAsDict")
public class StudentQueryAsDict extends AbstractBaseServlet{

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id =Integer.parseInt(req.getParameter("dictionaryKey"));
        List<Student> students = StudentDAO.queryAsDict(id);
        return students;
    }
}
