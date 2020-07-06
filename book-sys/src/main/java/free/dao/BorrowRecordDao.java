package free.dao;

import free.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BorrowRecordDao {


    public static List<BorrowRecord> query(){

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();

            String sql = "";

            ps = c.prepareStatement(sql);


            while(rs.next()){
                BorrowRecord br = new BorrowRecord();
                br.setId(rs.getInt("id"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
