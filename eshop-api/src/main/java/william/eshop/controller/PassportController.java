package william.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import william.eshop.rest.CommonRestResponse;
import william.eshop.service.UserService;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description 用户登录/注册API
 */
@RestController
@RequestMapping("/passport")
public class PassportController {
    @Autowired
    private UserService userService;

    @GetMapping("/existsByUsername")
    public CommonRestResponse<Boolean> existsByUsername(@RequestParam String username) {
        boolean exists = userService.existsByUsername(username);
        return CommonRestResponse.ok(exists);
    }
}
