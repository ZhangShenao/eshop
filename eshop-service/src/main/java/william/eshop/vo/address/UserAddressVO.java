package william.eshop.vo.address;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2020-04-28
 * @Description 收货地址
 */
@Data
@ApiModel(value = "UserAddressVO", description = "收货地址")
public class UserAddressVO {
    @ApiModelProperty(value = "主键", name = "地址id", required = true)
    private String id;

    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    private String userId;

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

    @ApiModelProperty(value = "是否默认地址", name = "defaultAddress", required = true)
    private boolean defaultAddress;
}
