package william.eshop.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import william.eshop.rest.CommonRestResponse;
import william.eshop.rest.ResultCode;

/**
 * @Author zhangshenao
 * @Date 2019-12-17
 * @Description 文件相关异常处理器
 */
@RestControllerAdvice
public class FileExceptionHandler {
    //当上传文件的大小超过上限时,捕获MaxUploadSizeExceededException异常
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public CommonRestResponse handlerMaxUploadFile(MaxUploadSizeExceededException e) {
        return CommonRestResponse.error(ResultCode.FILE_TOO_LARGE);
    }
}
