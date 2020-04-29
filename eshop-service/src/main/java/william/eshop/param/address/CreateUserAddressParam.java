package william.eshop.param.address;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import william.eshop.model.address.UserAddress;

/**
 * @Author zhangshenao
 * @Date 2020-04-29
 * @Description 创建收货地址参数
 */
@Data
@ApiModel(value = "CreateUserAddressParam", description = "创建收货地址参数")
public class CreateUserAddressParam {
    @ApiModelProperty(value = "收件人姓名", name = "receiver", required = true)
    private String receiver;

    @ApiModelProperty(value = "收件人手机号", name = "mobile", required = true)
    private String mobile;

    @ApiModelProperty(value = "省份", name = "province", required = true)
    private String province;

    @ApiModelProperty(value = "城市", name = "city", required = true)
    private String city;

    @ApiModelProperty(value = "区县", name = "district", required = true)
    private String district;

    @ApiModelProperty(value = "详细地址", name = "detail", required = true)
    private String detail;

    public boolean isIllegal() {
        return StringUtils.isEmpty(receiver) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(province)
                || StringUtils.isEmpty(city) || StringUtils.isEmpty(district) || StringUtils.isEmpty(detail);
    }

    public UserAddress toModel(String userId) {
        UserAddress model = new UserAddress();
        BeanUtils.copyProperties(this, model);
        model.setId(UUID.randomUUID().toString());
        model.setUserId(userId);
        model.setCreatedTime(new Date());
        model.setUpdatedTime(new Date());
        return model;
    }
}
