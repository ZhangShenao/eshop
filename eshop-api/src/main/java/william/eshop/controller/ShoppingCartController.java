package william.eshop.controller;

import static william.eshop.rest.ResultCode.USER_NOT_LOGIN;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import william.eshop.param.ShoppingCartParam;
import william.eshop.rest.CommonRestResponse;
import william.eshop.service.passport.PassportService;

/**
 * @Author zhangshenao
 * @Date 2019-12-07
 * @Description 购物车API
 */
@Api(value = "购物车相关接口")
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private PassportService passportService;

    @PutMapping("/add")
    @ApiOperation(value = "添加购物车", httpMethod = "POST")
    public CommonRestResponse addShoppingCart(@RequestBody ShoppingCartParam param, HttpServletRequest request) {
        //未登录状态下不处理购物车
        if (!passportService.isLogin(request)) {
            return CommonRestResponse.error(USER_NOT_LOGIN);
        }

        //TODO 将用户购物车信息保存到Redis中
        return CommonRestResponse.ok();
    }

    @PutMapping("/remove/{itemSpecId}")
    @ApiOperation(value = "添加购物车", httpMethod = "POST")
    public CommonRestResponse removeShoppingCart(@PathVariable String itemSpecId, HttpServletRequest request) {
        //未登录状态下不处理购物车
        Optional<String> userId = passportService.parseUserIdFromCookie(request);
        if (!userId.isPresent()) {
            return CommonRestResponse.error(USER_NOT_LOGIN);
        }
        //TODO 从Redis中删除购物车信息
        return CommonRestResponse.ok();
    }

}
