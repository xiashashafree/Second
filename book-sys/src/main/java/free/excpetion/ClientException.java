package free.excpetion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientException extends BaseException {
    public ClientException(String code, String message) {
        this(code, message,null);
    }

    public ClientException(String message, String code, Throwable cause) {
        super("SYS："+code, "客户端异常："+message, cause);
    }
}
