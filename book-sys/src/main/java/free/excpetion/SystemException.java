package free.excpetion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SystemException extends BaseException {
    public SystemException(String code, String message) {
       this(code, message,null);
    }

    public SystemException(String message, String code, Throwable cause) {
        super("SYS："+code, "服务端异常："+message, cause);
    }
}
