package free.excpetion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusinessException extends BaseException {
    public BusinessException(String code, String message) {
        this(code, message,null);
    }

    public BusinessException(String message, String code, Throwable cause) {
        super("SYS："+code, "客户端异常："+message, cause);
    }
}
