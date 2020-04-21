package william.eshop.service.user.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import william.eshop.mapper.user.UserMapper;
import william.eshop.model.user.User;
import william.eshop.param.UserParam;
import william.eshop.service.user.UserService;
import william.eshop.utils.MD5Utils;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description 用户服务
 */
@Service
@Slf4j
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

    @Override
    public Optional<User> login(String username, String password) {
        String encodedPassword = MD5Utils.encode(password);
        return Optional.ofNullable(userMapper.queryByUsernameAndPassword(username, encodedPassword));
    }

    @Override
    public Optional<User> queryById(String id) {
        return Optional.ofNullable(userMapper.selectByPrimaryKey(id));
    }

    @Override
    public boolean modifyUserInfo(String userId, UserParam param) {
        User model = userMapper.selectByPrimaryKey(userId);
        if (model == null) {
            return false;
        }

        if (StringUtils.hasText(param.getNickname())) {
            model.setNickname(param.getNickname());
        }
        if (StringUtils.hasText(param.getRealName())) {
            model.setRealName(param.getRealName());
        }
        if (StringUtils.hasText(param.getMobile())) {
            model.setMobile(param.getMobile());
        }
        if (StringUtils.hasText(param.getEmail())) {
            model.setEmail(param.getEmail());
        }
        model.setUpdatedTime(new Date());
        return (userMapper.updateByPrimaryKey(model) > 0);
    }

    @Override
    public boolean updateHeadUrl(String userId, String url) {
        User model = userMapper.selectByPrimaryKey(userId);
        if (model == null) {
            return false;
        }
        model.setFace(url);
        return (userMapper.updateByPrimaryKey(model) > 0);
    }
}
