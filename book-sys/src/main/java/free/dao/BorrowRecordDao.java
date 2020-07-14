package free.dao;

import free.excpetion.SystemException;
import free.model.*;
import free.util.CountHolder;
import free.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowRecordDAO {
    public static List<BorrowRecord> query(Page p) {
        List<BorrowRecord> records = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = DBUtil.getConnection();
            StringBuilder sql = new StringBuilder("select br.id," +
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
                    "         join classes c on s.classes_id = c.id");



            //搜素内容不为空字符串//占位符替换时会自动加上单引号
            if(p.getSearchText()!= null && p.getSearchText().trim().length()>0){
                sql.append(" where s.student_name like ? or b.book_name like ?");
            }

            //升序或降序
            if(p.getSortOrder() != null && p.getSortOrder().trim().length()>0){
                sql.append(" order by br.create_time" +p.getSortOrder());
            }
            ps = c.prepareStatement(sql.toString());



            StringBuilder countSql = new StringBuilder("select count(0) count from(");
            countSql.append(sql);
            countSql.append(") tmp");


            ps = c.prepareStatement(countSql.toString());

            if(p.getSearchText() != null && p.getSearchText().trim().length()>0){
                ps.setString(1,"%"+p.getSearchText()+"%");
                ps.setString(2,"%"+p.getSearchText()+"%");

            }
            rs = ps.executeQuery();
            while(rs.next()){
                int count = rs.getInt("count");
                CountHolder.set(count);
                //需要在返回的数据中将count设置为total
            }
            sql.append("   limit ?,?");
            ps= c.prepareStatement(sql.toString());
            int i=1;
            if(p.getSearchText()!= null && p.getSearchText().trim().length()>0){
                ps.setString(i++,"%"+p.getSearchText()+"%");
                ps.setString(i++,"%"+p.getSearchText()+"%");
            }
            ps.setInt(i++,(p.getPageNumber()-1)*p.getPageSize());
            ps.setInt(i++,p.getPageNumber());
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
                classes.setId(rs.getInt("classes_id"));
                classes.setClassesName(rs.getString("classes_name"));
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
                classes.setId(rs.getInt("classes_id"));
                classes.setClassesName(rs.getString("classes_name"));
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

    public static int insert(BorrowRecord record) {

        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            String sql = "insert into borrow_record(book_id,student_id,start_time,end_time) values(?,?,?,?) where id = ?";
            ps = c.prepareStatement(sql);

            ps.setInt(1,record.getId());
            ps.setInt(2,record.getStudentId());
            ps.setTimestamp(3,new Timestamp(record.getStartTime().getTime()));
            ps.setTimestamp(4,new Timestamp(record.getEndTime().getTime()));
            ps.setInt(5,record.getId());
            return  ps.executeUpdate();

        } catch (Exception e) {
            throw new SystemException("0000010", "查询图书信息出错");
        } finally {
            DBUtil.close(c, ps);
        }

    }

    public static int delete(String[] ids) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            StringBuilder sql = new StringBuilder("delete from borrow_record where id in (");
            for (int i = 0; i < ids.length ; i++) {
                if(i != 0){
                    sql.append(",");

                }
                sql.append("?");
            }
            sql.append(")");
            ps = c.prepareStatement(sql.toString());
            for (int i = 0; i < ids.length ; i++) {
                ps.setInt(i+1,Integer.parseInt(ids[i]));
            }

            return  ps.executeUpdate();

        } catch (Exception e) {
            throw new SystemException("0000010", "查询图书信息出错");
        } finally {
            DBUtil.close(c, ps);
        }
    }
}
