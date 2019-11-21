package william.eshop.controller;

import static william.eshop.rest.ResultCode.INVALID_PARAM;
import static william.eshop.rest.ResultCode.SUCCESS;
import static william.eshop.rest.ResultCode.USER_EXISTS;

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
import william.eshop.param.UserParam;
import william.eshop.rest.CommonRestResponse;
import william.eshop.rest.ResultCode;
import william.eshop.service.UserService;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description 用户登录/注册API
 */
@RestController
@RequestMapping("/passport")
@Api(value = "用户注册登录相关接口")
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
            return CommonRestResponse.error(USER_EXISTS);
        }
        userService.register(param);
        return CommonRestResponse.ok();
    }
}
