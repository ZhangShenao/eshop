package william.eshop.param;

import static william.eshop.constants.UserConstants.DEFAULT_BIRTHDAY_STRING;
import static william.eshop.constants.UserConstants.DEFAULT_GENDER;
import static william.eshop.constants.UserConstants.DEFAULT_USER_FACE;
import static william.eshop.constants.UserConstants.PASSWORD_MIN_LENGTH;
import static william.eshop.rest.ResultCode.EMPTY_USERNAME_OR_PASSWORD;
import static william.eshop.rest.ResultCode.PASSWORD_NOT_MATCH;
import static william.eshop.rest.ResultCode.PASSWORD_TOO_SHORT;
import static william.eshop.rest.ResultCode.SUCCESS;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import william.eshop.model.user.User;
import william.eshop.rest.ResultCode;
import william.eshop.utils.DateUtils;
import william.eshop.utils.MD5Utils;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description 用户请求参数
 */
@Data
@ApiModel(value = "UserParam", description = "用户请求参数")
public class UserParam {
    @ApiModelProperty(value = "用户名", name = "username", required = true)
    private String username;

    @ApiModelProperty(value = "密码", name = "password", required = true)
    private String password;

    @ApiModelProperty(value = "确认密码", name = "confirmPassword")
    private String confirmPassword;

    @NotBlank(message = "用户昵称不能为空")
    @Length(max = 12, message = "用户昵称不能超过12位")
    @ApiModelProperty(value = "用户昵称", name = "nickname", example = "杰森")
    private String nickname;

    @Length(max = 12, message = "用户真实姓名不能超过12位")
    @ApiModelProperty(value = "真实姓名", name = "realName", example = "杰森")
    private String realName;

    @Pattern(regexp = "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$", message = "手机号格式不正确")
    @ApiModelProperty(value = "手机号", name = "mobile", example = "13999999999")
    private String mobile;

    @Email
    @ApiModelProperty(value = "邮箱地址", name = "email", example = "imooc@imooc.com")
    private String email;

    @Min(value = 0, message = "性别选择不正确")
    @Max(value = 2, message = "性别选择不正确")
    @ApiModelProperty(value = "性别", name = "sex", example = "0:女 1:男 2:保密")
    private int sex;

    @ApiModelProperty(value = "生日", name = "birthday", example = "1900-01-01")
    private Date birthday;

    public ResultCode validate() {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(confirmPassword)) {
            return EMPTY_USERNAME_OR_PASSWORD;
        }
        if (password.length() < PASSWORD_MIN_LENGTH) {
            return PASSWORD_TOO_SHORT;
        }
        if (!password.equals(confirmPassword)) {
            return PASSWORD_NOT_MATCH;
        }
        return SUCCESS;
    }

    public User toDefaultModel() {
        User model = new User();

        //暂时使用UUID全局唯一id
        model.setId(UUID.randomUUID().toString());

        model.setUsername(username);

        //对密码进行MD5加密
        model.setPassword(MD5Utils.encode(password));

        //默认昵称与用户名一致
        model.setNickname(username);

        //默认头像
        model.setFace(DEFAULT_USER_FACE);

        //默认生日
        model.setBirthday(DateUtils.stringToDate(DEFAULT_BIRTHDAY_STRING));

        //默认性别
        model.setSex(DEFAULT_GENDER.getValue());

        //真实姓名
        Optional.ofNullable(realName).ifPresent(model::setRealName);

        model.setCreatedTime(new Date());
        model.setUpdatedTime(new Date());
        return model;
    }
}
