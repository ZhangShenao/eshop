package william.eshop.mapper.address;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import william.eshop.model.address.UserAddress;

/**
 * @Author zhangshenao
 * @Date 2020-04-28
 * @Description 收货地址Mapper
 */
public interface UserAddressMapper extends Mapper<UserAddress>, MySqlMapper<UserAddress> {
    List<UserAddress> listByUserId(@Param("userId") String userId);

    void batchUpdateDefault(@Param("userId") String userId, @Param("isDefault") int isDefault);
}
