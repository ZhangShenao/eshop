package william.eshop.service.item.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import william.eshop.mapper.item.ItemCommentMapper;
import william.eshop.model.item.ItemComment;
import william.eshop.service.item.ItemCommentService;

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
        return commentMapper.selectAll();
    }
}
