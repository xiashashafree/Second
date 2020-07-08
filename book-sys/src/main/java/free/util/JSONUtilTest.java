package free.util;

import free.model.ResponseResult;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

public class JSONUtilTest {
    @Test
    public void testRead(){
        InputStream is = getClass().getClassLoader().getResourceAsStream("response.json");
        ResponseResult rs = JSONUtil.read(is,ResponseResult.class);
        System.out.println(rs);
        Assert.assertNotNull(rs);

    }

    @Test
    public void testWrite(){
        ResponseResult r = new ResponseResult();
        r.setCode("666");
        r.setMessage("密室大逃脱");
        r.setSuccess(true);
        r.setTotal(4);
        String s = JSONUtil.write(r);
        System.out.println(s);
        Assert.assertNotNull(s);
        Assert.assertTrue(s.trim().length()>0);

    }
}
