package william.eshop.rest;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description 统一Rest响应状态码
 */
public enum ResultCode {
    SUCCESS(0, "SUCCESS"),
    INVALID_PARAM(1, "INVALID_PARAM"),
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
