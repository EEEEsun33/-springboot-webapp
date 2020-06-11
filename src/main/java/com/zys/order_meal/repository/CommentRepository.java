package com.zys.order_meal.repository;

import com.zys.order_meal.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * 评论的Dao接口
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    /**
     * 通过buyerId查询所有评论
     * @param buyerId
     * @return
     */

    List<Comment> findAllByBuyerId(Integer buyerId);
}
