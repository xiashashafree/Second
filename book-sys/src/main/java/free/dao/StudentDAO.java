package free.dao;

import free.excpetion.SystemException;
import free.model.Student;
import free.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static List<Student> queryAsDict(int id) {
        List<Student> students = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            c = DBUtil.getConnection();
            String sql = "select s.id,s.student_name,s.id_card,s.student_no from student s j" +
                    "oin classes c on s.classes_id = c.id where s.id=?;";
            ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while(rs.next()){
                Student student = new Student();
                student.setDictionaryTagKey(String.valueOf(rs.getInt("id")));
                student.setDictionaryTagValue(rs.getString("student_name"));
                student.setStudentNo("student_no");
                student.setIdCard("id_card");

                students.add(student);
            }
        }catch(Exception e){
            throw new SystemException("000003","查询班级信息出错");
        }finally{
            DBUtil.close(c,ps,rs);
        }
        return students;

    }
}
