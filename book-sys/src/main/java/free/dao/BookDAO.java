package free.dao;

import free.excpetion.SystemException;
import free.model.Book;
import free.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public static  List<Book> queryAsDict() {
        List<Book> books = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = DBUtil.getConnection();
            String sql = "";
            ps = c.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setDictionaryTagKey(rs.getString("dictionary_tag_key"));
                book.setDictionaryTagValue(rs.getString("dictionary_tag_value"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getBigDecimal("price"));
                books.add(book);
            }
        } catch (Exception e) {
            throw new SystemException("000004", "查询图书信息出错");
        } finally {
            DBUtil.close(c, ps, rs);
        }
        return books;
    }
}
