package william.eshop.service.carousel.impl;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.eshop.constants.CarouselShowStatus;
import william.eshop.mapper.carouse.CarouselMapper;
import william.eshop.model.carousel.Carousel;
import william.eshop.service.carousel.CarouselService;
import william.eshop.vo.carousel.CarouselVO;

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
    public List<CarouselVO> listByShowStatus(CarouselShowStatus showStatus) {
        return Optional.ofNullable(carouselMapper.listByShowStatus(showStatus.getValue()))
                .orElse(Collections.emptyList())
                .stream()
                .sorted(Comparator.comparingInt(Carousel::getSort)) //排序
                .map(Carousel::toVO)
                .collect(toList());
    }
}
