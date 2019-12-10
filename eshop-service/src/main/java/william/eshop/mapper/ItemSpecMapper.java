package william.eshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import william.eshop.model.ItemSpec;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 商品规格Mapper
 */
public interface ItemSpecMapper extends Mapper<ItemSpec>, MySqlMapper<ItemSpec> {
    List<ItemSpec> listInIds(@Param("ids") List<String> ids);

    /**
     * 商品减库存
     * 采用乐观锁的机制,只有当库存足够时才执行扣减,通过返回值判断是否扣减成功
     */
    int reduceStock(@Param("id") String id, @Param("reduceCount") int reduceCount);
}
