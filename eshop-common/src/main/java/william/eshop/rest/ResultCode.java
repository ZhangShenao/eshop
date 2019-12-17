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
    DUPLICATE_USERNAME(5, "用户名已存在"),
    USER_NOT_EXISTS(6, "用户不存在"),
    ITEM_NOT_EXISTS(7, "商品不存在"),
    USER_NOT_LOGIN(8, "用户不存在"),
    PAY_METHOD_NOT_SUPPORTED(9, "不支持的支付方式"),
    CREATE_ORDER_FAIL(10, "创建订单失败"),
    NOT_LOGIN(11, "用户未登录"),
    ORDER_NOT_EXISTS(12, "订单不存在"),
    ILLEGAL_FILE_STATUS(13, "文件状态异常"),
    UNSUPPORTED_FILE_TYPE(14, "文件类型不支持"),
    UPLOAD_FILE_FAIL(15, "文件上传失败,请稍后重试"),
    FILE_TOO_LARGE(16, "文件大小超出上限,请压缩后重试"),;

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
