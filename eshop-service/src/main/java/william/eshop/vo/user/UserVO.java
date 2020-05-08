package william.eshop.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-11-25
 * @Description 用户信息VO
 */
@Data
@ApiModel(value = "UserVO", description = "用户信息VO")
public class UserVO {
    @ApiModelProperty(value = "用户id", name = "id", required = true)
    private String id;

    @ApiModelProperty(value = "用户名", name = "username", required = true)
    private String username;

    @ApiModelProperty(value = "用户昵称", name = "nickname", required = true)
    private String nickname;

    @ApiModelProperty(value = "用户头像", name = "face", required = true)
    private String face;
}
