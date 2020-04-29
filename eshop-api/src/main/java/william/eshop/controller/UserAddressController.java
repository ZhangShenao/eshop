package william.eshop.controller;

import static java.util.stream.Collectors.toList;
import static william.eshop.rest.ResultCode.INVALID_PARAM;
import static william.eshop.rest.ResultCode.NOT_LOGIN;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import william.eshop.model.address.UserAddress;
import william.eshop.param.address.ChangeDefaultAddressParam;
import william.eshop.param.address.CreateUserAddressParam;
import william.eshop.rest.CommonRestResponse;
import william.eshop.service.address.UserAddressService;
import william.eshop.service.passport.PassportService;
import william.eshop.vo.address.UserAddressVO;

/**
 * @Author zhangshenao
 * @Date 2020-04-28
 * @Description
 */
@Api(value = "收货地址相关接口", tags = "收货地址相关接口")
@RestController
@RequestMapping("/address")
public class UserAddressController {
    @Autowired
    private PassportService passportService;

    @Autowired
    private UserAddressService service;

    @GetMapping("/listByUser")
    @ApiOperation(value = "查询用户的收货地址列表", httpMethod = "GET")
    public CommonRestResponse<List<UserAddressVO>> listByUser(HttpServletRequest request) {
        Optional<String> userId = passportService.parseUserIdFromCookie(request);
        if (!userId.isPresent()) {
            return CommonRestResponse.error(NOT_LOGIN);
        }
        List<UserAddressVO> addresses = service.listByUserId(userId.get()).stream()
                .map(UserAddress::toVO)
                .collect(toList());
        return CommonRestResponse.ok(addresses);
    }

    @PostMapping
    @ApiOperation(value = "新增收货地址", httpMethod = "POST")
    public CommonRestResponse create(@RequestBody CreateUserAddressParam param, HttpServletRequest request) {
        Optional<String> userId = passportService.parseUserIdFromCookie(request);
        if (!userId.isPresent()) {
            return CommonRestResponse.error(NOT_LOGIN);
        }
        if (param.isIllegal()) {
            return CommonRestResponse.error(INVALID_PARAM);
        }
        boolean succ = service.create(userId.get(), param);
        return succ ? CommonRestResponse.ok() : CommonRestResponse.error(NOT_LOGIN);
    }

    @PutMapping("/changeDefault")
    @ApiOperation(value = "修改默认收货地址", httpMethod = "PUT")
    public CommonRestResponse changeDefault(@RequestBody ChangeDefaultAddressParam param, HttpServletRequest request) {
        Optional<String> userId = passportService.parseUserIdFromCookie(request);
        if (!userId.isPresent()) {
            return CommonRestResponse.error(NOT_LOGIN);
        }
        if (param.isIllegal()) {
            return CommonRestResponse.error(INVALID_PARAM);
        }
        boolean succ = service.changeDefault(userId.get(), param);
        return succ ? CommonRestResponse.ok() : CommonRestResponse.error(NOT_LOGIN);
    }
}
