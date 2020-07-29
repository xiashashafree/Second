package api;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Util {
    public static String readBody(HttpServletRequest request) throws UnsupportedEncodingException {
        //body的长度，单位是字节
        int contentLength = request.getContentLength();
        byte[] buffer = new byte[contentLength];
        try(InputStream inputStream = request.getInputStream()){
            inputStream.read(buffer,0,contentLength);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(buffer,0,contentLength,"UTF-8");
    }
}
