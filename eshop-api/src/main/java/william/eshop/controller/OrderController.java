package william.eshop.controller;

import static william.eshop.rest.ResultCode.CREATE_ORDER_FAIL;
import static william.eshop.rest.ResultCode.INVALID_PARAM;
import static william.eshop.rest.ResultCode.NOT_LOGIN;
import static william.eshop.rest.ResultCode.PAY_METHOD_NOT_SUPPORTED;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import william.eshop.constants.PayMethod;
import william.eshop.param.CommitOrderParam;
import william.eshop.rest.CommonRestResponse;
import william.eshop.service.service.OrderService;
import william.eshop.service.service.PassportService;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 订单API
 */
@Api(value = "订单相关接口")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PassportService passportService;

    @PostMapping("/commit")
    @ApiOperation(value = "提交订单", httpMethod = "POST")
    public CommonRestResponse commit(@RequestBody CommitOrderParam param, HttpServletRequest request) {
        //1. 校验登录状态
        if (!passportService.isLogin(request)) {
            return CommonRestResponse.error(NOT_LOGIN);

        }
        //2. 校验支付方式
        PayMethod payMethod = PayMethod.getByValue(param.getPayMethod());
        if (!payMethod.isSupported()) {
            return CommonRestResponse.error(PAY_METHOD_NOT_SUPPORTED);
        }

        //2. 校验提交参数
        if (param.isIllegal()) {
            return CommonRestResponse.error(INVALID_PARAM);
        }

        //4. 创建订单
        Optional<String> orderId = orderService.create(param);
        if (!orderId.isPresent()) {
            return CommonRestResponse.error(CREATE_ORDER_FAIL);
        }

        //4.TODO 从用户购物车中移除已下单的商品
        return CommonRestResponse.ok(orderId.get());
    }
}
