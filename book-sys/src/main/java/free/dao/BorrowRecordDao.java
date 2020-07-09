package free.dao;

import free.excpetion.SystemException;
import free.model.Book;
import free.model.BorrowRecord;
import free.model.Classes;
import free.model.Student;
import free.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowRecordDAO {
    public static List<BorrowRecord> query() {
        List<BorrowRecord> records = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = DBUtil.getConnection();
            String sql = "select br.id," +
                    "       br.book_id," +
                    "       br.student_id," +
                    "       br.start_time," +
                    "       br.end_time," +
                    "       br.create_time," +
                    "       b.book_name," +
                    "       b.author," +
                    "       b.price," +
                    "       s.student_name," +
                    "       s.student_no," +
                    "       s.id_card," +
                    "       s.student_email," +
                    "       s.classes_id," +
                    "       c.classes_name," +
                    "       c.classes_graduate_year," +
                    "       c.classes_major," +
                    "       c.classes_desc" +
                    "  from borrow_record br" +
                    "         join book b on br.book_id = b.id" +
                    "         join student s on br.student_id = s.id" +
                    "         join classes c on s.classes_id = c.id";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                BorrowRecord br = new BorrowRecord();
                br.setId(rs.getInt("id"));
                br.setStartTime(new Date(rs.getTimestamp("start_time").getTime()));
                br.setEndTime(new Date(rs.getTimestamp("end_time").getTime()));
                br.setCreateTime(new Date(rs.getTimestamp("create_time").getTime()));
                Book b = new Book();
                b.setId(rs.getInt("book_id"));
                b.setAuthor(rs.getString("author"));
                b.setBookName(rs.getString("book_name"));
                b.setPrice(rs.getBigDecimal("price"));
                br.setBook(b);

                Student s = new Student();
                s.setId(rs.getInt("student_id"));
                s.setStudentName(rs.getString("student_name"));
                s.setStudentEmail(rs.getString("student_email"));
                s.setStudentNo(rs.getString("student_no"));
                s.setIdCard(rs.getString("id_card"));
                br.setStudent(s);
                Classes classes = new Classes();
                classes.setId(rs.getInt("class_id"));
                classes.setClassesName(rs.getString("class_name"));
                classes.setClassesGraduateYear(rs.getString("classes_graduate_year"));
                classes.setClassesDesc(rs.getString("classes_desc"));
                classes.setClassesMajor(rs.getString("classes_major"));
                br.setClasses(classes);
                records.add(br);
            }
        }catch(Exception e){
            throw new SystemException("000001","查询图书借阅信息出错");
        }finally{
            DBUtil.close(c,ps,rs);
        }
        return records;
    }

    public static BorrowRecord queryById(int id) {

       BorrowRecord br = new BorrowRecord();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = DBUtil.getConnection();
            String sql = "select br.id," +
                    "       br.book_id," +
                    "       br.student_id," +
                    "       br.start_time," +
                    "       br.end_time," +
                    "       br.create_time," +
                    "       b.book_name," +
                    "       b.author," +
                    "       b.price," +
                    "       s.student_name," +
                    "       s.student_no," +
                    "       s.id_card," +
                    "       s.student_email," +
                    "       s.classes_id," +
                    "       c.classes_name," +
                    "       c.classes_graduate_year," +
                    "       c.classes_major," +
                    "       c.classes_desc" +
                    "  from borrow_record br" +
                    "         join book b on br.book_id = b.id" +
                    "         join student s on br.student_id = s.id" +
                    "         join classes c on s.classes_id = c.id where br.id = ?";
            ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while(rs.next()){

                br.setId(rs.getInt("id"));
                br.setStartTime(new Date(rs.getTimestamp("start_time").getTime()));
                br.setEndTime(new Date(rs.getTimestamp("end_time").getTime()));
                br.setCreateTime(new Date(rs.getTimestamp("create_time").getTime()));
                Book b = new Book();
                b.setId(rs.getInt("book_id"));
                b.setAuthor(rs.getString("author"));
                b.setBookName(rs.getString("book_name"));
                b.setPrice(rs.getBigDecimal("price"));
                br.setBook(b);

                Student s = new Student();
                s.setId(rs.getInt("student_id"));
                s.setStudentName(rs.getString("student_name"));
                s.setStudentEmail(rs.getString("student_email"));
                s.setStudentNo(rs.getString("student_no"));
                s.setIdCard(rs.getString("id_card"));
                br.setStudent(s);
                Classes classes = new Classes();
                classes.setId(rs.getInt("class_id"));
                classes.setClassesName(rs.getString("class_name"));
                classes.setClassesGraduateYear(rs.getString("classes_graduate_year"));
                classes.setClassesDesc(rs.getString("classes_desc"));
                classes.setClassesMajor(rs.getString("classes_major"));
                br.setClasses(classes);
            }
        }catch(Exception e){
            throw new SystemException("000001","查询图书借阅信息出错");
        }finally{
            DBUtil.close(c,ps,rs);
        }
        return br;
    }
}
