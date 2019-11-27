package william.eshop.service;

import java.util.List;

import william.eshop.constants.CarouselShowStatus;
import william.eshop.vo.CarouselVO;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description 轮播图服务
 */
public interface CarouselService {
    /**
     * 根据展示状态查询轮播图列表
     */
    List<CarouselVO> listByShowStatus(CarouselShowStatus showStatus);
}
