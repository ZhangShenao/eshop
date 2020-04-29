package william.eshop.service.address;

import java.util.List;

import william.eshop.model.address.UserAddress;
import william.eshop.param.address.ChangeDefaultAddressParam;
import william.eshop.param.address.CreateUserAddressParam;

/**
 * @Author zhangshenao
 * @Date 2020-04-28
 * @Description 收货地址服务
 */
public interface UserAddressService {
    //查询用户的收货地址列表
    List<UserAddress> listByUserId(String userId);

    //创建收货地址
    boolean create(String userId, CreateUserAddressParam param);

    //修改默认地址
    boolean changeDefault(String userId, ChangeDefaultAddressParam param);
}
