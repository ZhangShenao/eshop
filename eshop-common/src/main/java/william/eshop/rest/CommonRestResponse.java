package william.eshop.rest;


import static william.eshop.rest.ResultCode.SUCCESS;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author zhangshenao
 * @Date 2019-10-11
 * @Description 统一rest接口响应
 */
@Getter
@Setter
public class CommonRestResponse<T> {
    private int result;

    private String errorMsg;

    private T data;

    public static CommonRestResponse ok() {
        CommonRestResponse response = new CommonRestResponse();
        response.result = SUCCESS.getCode();
        response.errorMsg = SUCCESS.getMsg();
        response.data = SUCCESS.getMsg();
        return response;
    }

    public static <T> CommonRestResponse<T> ok(T data) {
        CommonRestResponse<T> response = new CommonRestResponse<>();
        response.result = SUCCESS.getCode();
        response.errorMsg = SUCCESS.getMsg();
        response.data = data;
        return response;
    }


    public static CommonRestResponse error(ResultCode resultCode) {
        CommonRestResponse response = new CommonRestResponse();
        response.result = resultCode.getCode();
        response.errorMsg = resultCode.getMsg();
        response.data = resultCode.getMsg();
        return response;
    }

    public static CommonRestResponse error(ResultCode resultCode, String errorMsg) {
        CommonRestResponse response = new CommonRestResponse();
        response.result = resultCode.getCode();
        response.errorMsg = errorMsg;
        response.data = resultCode.getMsg();
        return response;
    }

    public static <T> CommonRestResponse<T> error(ResultCode resultCode, T data) {
        CommonRestResponse<T> response = new CommonRestResponse<>();
        response.result = resultCode.getCode();
        response.errorMsg = resultCode.getMsg();
        response.data = data;
        return response;
    }
}
