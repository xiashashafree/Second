package free.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * http请求返回响应的统一格式
 * 具体需要的字段，是前端后端约定好的
 */
@Getter
@Setter
@ToString
public class ResponseResult {

    private boolean success;//前端响应状态200，但是success可以为false
    private String code;
    private String message;//自定义消息内容
    private Integer total;//分页需要的字段：查询出来的总行数
    private Object data;//业务数据
    private String stackTrace;//堆栈信息
}
