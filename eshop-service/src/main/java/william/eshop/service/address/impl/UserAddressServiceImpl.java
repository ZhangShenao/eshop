package william.eshop.service.address.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import william.eshop.constants.CommonFlag;
import william.eshop.mapper.address.UserAddressMapper;
import william.eshop.model.address.UserAddress;
import william.eshop.param.address.ChangeDefaultAddressParam;
import william.eshop.param.address.CreateUserAddressParam;
import william.eshop.service.address.UserAddressService;

/**
 * @Author zhangshenao
 * @Date 2020-04-28
 * @Description 收货地址服务
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private UserAddressMapper mapper;

    @Override
    public List<UserAddress> listByUserId(String userId) {
        return Optional.ofNullable(mapper.listByUserId(userId))
                .orElse(Collections.emptyList());
    }

    @Override
    public boolean create(String userId, CreateUserAddressParam param) {
        UserAddress model = param.toModel(userId);

        //如果用户收货地址为空,则设为默认地址
        List<UserAddress> existed = mapper.listByUserId(userId);
        if (CollectionUtils.isEmpty(existed)) {
            model.setIsDefault(CommonFlag.YES.getValue());
        }
        return mapper.insert(model) > 0;
    }

    @Override
    public boolean changeDefault(String userId, ChangeDefaultAddressParam param) {
        //如果设置为默认,则首先将所有地址设置为非默认
        if (param.isDefaultAddress()) {
            mapper.batchUpdateDefault(userId, CommonFlag.NO.getValue());
        }

        //更新地址状态
        String addressId = param.getId();
        UserAddress model = mapper.selectByPrimaryKey(addressId);
        if (model == null) {
            return false;
        }
        model.setIsDefault(param.isDefaultAddress() ? CommonFlag.YES.getValue() : CommonFlag.NO.getValue());
        return mapper.updateByPrimaryKey(model) > 0;
    }
}
