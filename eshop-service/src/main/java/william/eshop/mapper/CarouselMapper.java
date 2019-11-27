package william.eshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import william.eshop.model.Carousel;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description 轮播图Mapper
 */
public interface CarouselMapper extends Mapper<Carousel>, MySqlMapper<Carousel> {
    List<Carousel> listByShowStatus(@Param("showStatus") int showStatus);
}
