package william.eshop.rest;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description 统一Rest响应状态码
 */
public enum ResultCode {
    SUCCESS(0, "SUCCESS"),
    INVALID_PARAM(1, "非法参数"),
    EMPTY_USERNAME_OR_PASSWORD(2, "用户名或密码为空"),
    PASSWORD_NOT_MATCH(3, "两次输入密码不一致"),
    PASSWORD_TOO_SHORT(4, "密码长度过短"),
    USER_EXISTS(5, "用户已存在"),
    ;

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}