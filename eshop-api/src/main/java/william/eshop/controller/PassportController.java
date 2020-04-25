package william.eshop.controller;

import static william.eshop.constants.UserConstants.USER_ID_COOKIE_KEY;
import static william.eshop.rest.ResultCode.DUPLICATE_USERNAME;
import static william.eshop.rest.ResultCode.INVALID_PARAM;
import static william.eshop.rest.ResultCode.SUCCESS;
import static william.eshop.rest.ResultCode.USER_NOT_EXISTS;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import william.eshop.model.user.User;
import william.eshop.param.user.UserParam;
import william.eshop.rest.CommonRestResponse;
import william.eshop.rest.ResultCode;
import william.eshop.service.user.UserService;
import william.eshop.utils.CookieUtils;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description 用户登录/注册API
 */
@RestController
@RequestMapping("/passport")
@Api(value = "用户注册登录相关接口", tags = "用户注册登录相关接口")
//@ApiIgnore 生成Swagger文档时忽略此API
public class PassportController {
    @Autowired
    private UserService userService;

    @GetMapping("/existsByUsername")
    @ApiOperation(value = "用户名是否存在", httpMethod = "GET")
    public CommonRestResponse existsByUsername(@RequestParam String username) {
        if (StringUtils.isEmpty(username)) {
            return CommonRestResponse.error(INVALID_PARAM);
        }
        boolean exists = userService.existsByUsername(username);
        return CommonRestResponse.ok(exists);
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", httpMethod = "POST")
    public CommonRestResponse register(@RequestBody UserParam param) {
        ResultCode code = param.validate();
        if (SUCCESS != code) {
            return CommonRestResponse.error(code);
        }
        if (userService.existsByUsername(param.getUsername())) {
            return CommonRestResponse.error(DUPLICATE_USERNAME);
        }
        userService.register(param);
        return CommonRestResponse.ok();
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    public CommonRestResponse login(@RequestBody UserParam param, HttpServletResponse response) {
        String username = param.getUsername();
        String password = param.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return CommonRestResponse.error(INVALID_PARAM);
        }

        Optional<User> user = userService.login(username, password);
        if (!user.isPresent()) {
            return CommonRestResponse.error(USER_NOT_EXISTS);
        }

        User model = user.get();

        //将userId保存在Cookie中
        CookieUtils.setCookie(response, USER_ID_COOKIE_KEY, model.getId());
        return CommonRestResponse.ok(model.toVO());
    }

    @PostMapping("/logout")
    @ApiOperation(value = "用户退出登录", httpMethod = "POST")
    public CommonRestResponse logout(HttpServletResponse response) {
        //清除Cookie中的userId
        CookieUtils.removeCookie(response, USER_ID_COOKIE_KEY);
        return CommonRestResponse.ok();
    }
}
