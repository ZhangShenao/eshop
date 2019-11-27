package william.eshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.eshop.constants.CarouselShowStatus;
import william.eshop.mapper.CarouselMapper;
import william.eshop.model.Carousel;
import william.eshop.service.CarouselService;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description
 */
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> listByShowStatus(CarouselShowStatus showStatus) {
        return carouselMapper.listByShowStatus(showStatus.getValue());
    }
}
