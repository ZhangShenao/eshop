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
import java.util.UUID;

import org.springframework.util.StringUtils;

import lombok.Data;
import william.eshop.model.User;
import william.eshop.rest.ResultCode;
import william.eshop.utils.DateUtils;
import william.eshop.utils.MD5Utils;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description 用户请求参数
 */
@Data
public class UserParam {
    private String username;
    private String password;
    private String confirmPassword;

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

        model.setCreatedTime(new Date());
        model.setUpdatedTime(new Date());
        return model;
    }
}
