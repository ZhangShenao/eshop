package william.eshop.mapper;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import william.eshop.model.User;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description 用户Mapper
 */
public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
    User queryByUsername(@Param("username") String username);
}
