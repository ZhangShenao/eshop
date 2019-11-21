package william.eshop.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.eshop.mapper.UserMapper;
import william.eshop.model.User;
import william.eshop.param.UserParam;
import william.eshop.service.UserService;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description 用户服务
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<User> queryByUsername(String username) {
        return Optional.ofNullable(userMapper.queryByUsername(username));
    }

    @Override
    public boolean existsByUsername(String username) {
        return queryByUsername(username).isPresent();
    }

    @Override
    public User register(UserParam param) {
        User user = param.toDefaultModel();
        userMapper.insert(user);
        return user;
    }
}
