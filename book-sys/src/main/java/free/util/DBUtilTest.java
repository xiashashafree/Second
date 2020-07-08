package free.util;


import org.junit.Assert;
import org.junit.Test;

public class DBUtilTest {
    @Test
    public void testRead(){
        Assert.assertNotNull(DBUtil.getConnection());//测试数据库连接
    }

}
