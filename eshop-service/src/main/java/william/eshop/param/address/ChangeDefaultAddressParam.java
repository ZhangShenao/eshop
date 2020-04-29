package william.eshop.param.address;

import org.springframework.util.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2020-04-29
 * @Description
 */
@Data
@ApiModel(value = "ChangeDefaultAddressParam", description = "修改默认地址参数")
public class ChangeDefaultAddressParam {
    @ApiModelProperty(value = "地址id", name = "id", required = true)
    private String id;

    @ApiModelProperty(value = "是否为默认地址", name = "defaultAddress", required = true)
    private boolean defaultAddress;

    public boolean isIllegal() {
        return StringUtils.isEmpty(id);
    }
}
