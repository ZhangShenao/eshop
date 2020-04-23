package william.eshop.service.item.impl;

import static william.eshop.constants.CommentLevel.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.eshop.constants.CommentLevel;
import william.eshop.mapper.item.ItemCommentMapper;
import william.eshop.model.item.ItemComment;
import william.eshop.service.item.ItemCommentService;
import william.eshop.vo.item.ItemCommentStatisticsVO;

/**
 * @Author zhangshenao
 * @Date 2019-12-03
 * @Description 商品评价服务
 */
@Service
public class ItemCommentServiceImpl implements ItemCommentService {
    @Autowired
    private ItemCommentMapper commentMapper;

    @Override
    public List<ItemComment> listByItem(String itemId) {
        return commentMapper.listByItemId(itemId);
    }

    @Override
    public ItemCommentStatisticsVO countByItem(String itemId) {
        ItemCommentStatisticsVO vo = new ItemCommentStatisticsVO();
        Optional.ofNullable(commentMapper.countByItem(itemId)).ifPresent(vo::setTotal);
        Optional.ofNullable(commentMapper.countByItemAndLevel(itemId, GOOD.getValue())).ifPresent(vo::setGood);
        Optional.ofNullable(commentMapper.countByItemAndLevel(itemId, NORMAL.getValue())).ifPresent(vo::setNormal);
        Optional.ofNullable(commentMapper.countByItemAndLevel(itemId, BAD.getValue())).ifPresent(vo::setBad);
        return vo;
    }
}
