package william.eshop.controller;

import static william.eshop.rest.ResultCode.INVALID_PARAM;
import static william.eshop.rest.ResultCode.SHOPPING_CART_NOT_EXISTS;
import static william.eshop.rest.ResultCode.USER_NOT_LOGIN;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import william.eshop.constants.UserConstants;
import william.eshop.param.shoppingcart.ShoppingCartParam;
import william.eshop.rest.CommonRestResponse;
import william.eshop.service.passport.PassportService;
import william.eshop.service.shoppingcart.ShoppingCartService;
import william.eshop.utils.CookieUtils;
import william.eshop.utils.JsonUtils;
import william.eshop.vo.shoppingcart.ShoppingCartVO;

/**
 * @Author zhangshenao
 * @Date 2019-12-07
 * @Description 购物车API
 */
@Api(value = "购物车相关接口", tags = "购物车相关接口")
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private PassportService passportService;

    @Autowired
    private ShoppingCartService service;


    @PutMapping("/add")
    @ApiOperation(value = "添加购物车", httpMethod = "PUT")
    public CommonRestResponse add(@RequestBody ShoppingCartParam param, HttpServletRequest request,
            HttpServletResponse response) {
        //未登录状态下不处理购物车
        Optional<String> uid = passportService.parseUserIdFromCookie(request);
        if (!uid.isPresent()) {
            return CommonRestResponse.error(USER_NOT_LOGIN);
        }

        if (param.isIllegal()) {
            return CommonRestResponse.error(INVALID_PARAM);
        }

        //TODO 目前先将购物车数据保存的Cookie中,后面会改完保存到Redis中
        String cookieKey = String.format(UserConstants.USER_SHOPPING_CART_COOKIE_KEY_FORMAT, uid.get());
        CookieUtils.setCookie(response, cookieKey, JsonUtils.toJson(param));
        return CommonRestResponse.ok();
    }

    @PutMapping("/remove/{itemSpecId}")
    @ApiOperation(value = "删除购物车中的商品", httpMethod = "PUT")
    public CommonRestResponse remove(@PathVariable String itemSpecId, HttpServletRequest request,
            HttpServletResponse response) {
        //未登录状态下不处理购物车
        Optional<String> uid = passportService.parseUserIdFromCookie(request);
        if (!uid.isPresent()) {
            return CommonRestResponse.error(USER_NOT_LOGIN);
        }

        //TODO 从Redis中删除购物车信息
        String cookieKey = String.format(UserConstants.USER_SHOPPING_CART_COOKIE_KEY_FORMAT, uid.get());
        Optional<String> cookie = CookieUtils.getCookieValue(request, cookieKey);
        if (!cookie.isPresent()) {
            return CommonRestResponse.error(SHOPPING_CART_NOT_EXISTS);
        }
        ShoppingCartParam cart = JsonUtils.parseFrom(cookie.get(), ShoppingCartParam.class);
        cart.remove(itemSpecId);
        CookieUtils.setCookie(response, cookieKey, JsonUtils.toJson(cart));
        return CommonRestResponse.ok();
    }

    @GetMapping("/detail")
    @ApiOperation(value = "查看购物车详情", httpMethod = "GET")
    public CommonRestResponse<ShoppingCartVO> detail(HttpServletRequest request) {
        //未登录状态下不处理购物车
        Optional<String> uid = passportService.parseUserIdFromCookie(request);
        if (!uid.isPresent()) {
            return CommonRestResponse.error(USER_NOT_LOGIN);
        }

        String cookieKey = String.format(UserConstants.USER_SHOPPING_CART_COOKIE_KEY_FORMAT, uid.get());
        Optional<String> cookie = CookieUtils.getCookieValue(request, cookieKey);
        if (!cookie.isPresent()) {
            return CommonRestResponse.error(SHOPPING_CART_NOT_EXISTS);
        }
        ShoppingCartParam cart = JsonUtils.parseFrom(cookie.get(), ShoppingCartParam.class);
        return CommonRestResponse.ok(service.shoppingCartInfo(cart));
    }

}
