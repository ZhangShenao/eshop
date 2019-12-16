package william.eshop.controller;

import static william.eshop.rest.ResultCode.USER_NOT_EXISTS;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import william.eshop.model.User;
import william.eshop.param.UserParam;
import william.eshop.rest.CommonRestResponse;
import william.eshop.service.UserService;

/**
 * @Author zhangshenao
 * @Date 2019-12-16
 * @Description 用户中心API
 */
@RestController
@RequestMapping("/uc")
@Api(value = "用户中心相关接口")
public class UserCenterController {
    @Autowired
    private UserService userService;

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
}
