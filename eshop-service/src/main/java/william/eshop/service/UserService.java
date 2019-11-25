package william.eshop.service;

import java.util.Optional;

import william.eshop.model.User;
import william.eshop.param.UserParam;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description 用户服务
 */
public interface UserService {
    /**
     * 根据用户名查询用户
     */
    Optional<User> queryByUsername(String username);

    /**
     * 判断指定用户名是否存在
     */
    boolean existsByUsername(String username);

    /**
     * 用户注册
     */
    User register(UserParam param);

    /**
     * 用户登录
     */
    Optional<User> login(String username, String password);
}
