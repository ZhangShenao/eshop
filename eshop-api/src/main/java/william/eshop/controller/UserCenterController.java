package william.eshop.controller;

import static william.eshop.rest.ResultCode.ILLEGAL_FILE_STATUS;
import static william.eshop.rest.ResultCode.UNSUPPORTED_FILE_TYPE;
import static william.eshop.rest.ResultCode.UPLOAD_FILE_FAIL;
import static william.eshop.rest.ResultCode.USER_NOT_EXISTS;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import william.eshop.model.user.User;
import william.eshop.param.user.UserParam;
import william.eshop.resource.FileUploadResource;
import william.eshop.rest.CommonRestResponse;
import william.eshop.service.user.UserService;
import william.eshop.utils.FileUtils;

/**
 * @Author zhangshenao
 * @Date 2019-12-16
 * @Description 用户中心API
 */
@RestController
@RequestMapping("/uc")
@Api(value = "用户中心相关接口")
@Slf4j
public class UserCenterController {
    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadResource fileUploadResource;

    @GetMapping("/userInfo/{userId}")
    @ApiOperation(value = "查询用户信息", httpMethod = "GET")
    public CommonRestResponse userInfo(@PathVariable String userId) {
        Optional<User> user = userService.queryById(userId);
        if (!user.isPresent()) {
            return CommonRestResponse.error(USER_NOT_EXISTS);
        }
        return CommonRestResponse.ok(user.get().toVO());
    }

    @PutMapping("/userInfo/{userId}")
    @ApiOperation(value = "修改用户信息", httpMethod = "PUT")
    public CommonRestResponse modifyUserInfo(@PathVariable String userId, @Valid @RequestBody UserParam param,
            BindingResult bindingResult) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            return CommonRestResponse.error(bindingResult);
        }
        userService.modifyUserInfo(userId, param);
        return CommonRestResponse.ok();
    }

    @PutMapping("/updateHead/{userId}")
    @ApiOperation(value = "修改头像", httpMethod = "PUT")
    public CommonRestResponse updateHead(@PathVariable String userId, @RequestParam MultipartFile file) {
        //文件类型校验
        if (file == null || StringUtils.isEmpty(file.getOriginalFilename())) {
            return CommonRestResponse.error(ILLEGAL_FILE_STATUS);
        }
        if (!FileUtils.isLegalImage(file)) {
            return CommonRestResponse.error(UNSUPPORTED_FILE_TYPE);
        }

        //拼接上传目录
        String basePath = fileUploadResource.getBasePath();
        String path = basePath + "/" + file.getOriginalFilename();
        try (FileOutputStream out = new FileOutputStream(path); InputStream in = file.getInputStream()) {
            IOUtils.copy(in, out);
        } catch (Exception e) {
            log.error("Upload File Error! ", e);
            return CommonRestResponse.error(UPLOAD_FILE_FAIL);
        }

        // 由于浏览器可能存在缓存的情况，所以在这里，我们需要加上时间戳来保证更新后的图片可以及时刷新
        String url = fileUploadResource.getImageServerUrl() + path + "?ts=" + System.currentTimeMillis();
        userService.updateHeadUrl(userId, url);
        return CommonRestResponse.ok();
    }
}
