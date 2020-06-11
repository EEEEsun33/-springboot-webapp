package com.zys.order_meal.controller;

import com.zys.order_meal.dto.OrderDTO;
import com.zys.order_meal.entity.Comment;
import com.zys.order_meal.entity.OrderMaster;
import com.zys.order_meal.enums.OrderStatusEnum;
import com.zys.order_meal.enums.ResultEnum;
import com.zys.order_meal.exception.SellException;
import com.zys.order_meal.repository.CommentRepository;
import com.zys.order_meal.repository.OrderMasterRepository;
import com.zys.order_meal.service.OrderService;
import com.zys.order_meal.utils.ResultVOUtil;
import com.zys.order_meal.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 评论的controller层
 */
@RestController
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMasterRepository masterRepository;

    @RequestMapping("/comment")
    public ResultVo<Comment> detail(@RequestParam("buyerid") Integer buyerid ,
                                    @RequestParam("orderId") String orderId ,
                                    @RequestParam("username") String username ,
                                    @RequestParam("content") String content){


        if (buyerid ==null ) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        //提交评论
        Comment comment = new Comment();
        comment.setCommentName(username);
        comment.setBuyerId(buyerid);
        comment.setCommentContent(content);
        comment.setOrderId(orderId);
        Comment save = commentRepository.save(comment);

        //修改订单状态
        OrderDTO orderDTO = orderService.findOne(orderId);
        orderDTO.setOrderStatus(OrderStatusEnum.COMMENT.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO , orderMaster);
        OrderMaster updateResult = masterRepository.save(orderMaster);
        return ResultVOUtil.success(save);
    }

    @GetMapping("/allComment")
    public ModelAndView commentList(Map<String , Object> map){
        List<Comment> all = commentRepository.findAll();
        map.put("commentList",all);
        return new ModelAndView("comment/list", map);
    }

    //单个用户的所有评论
    @GetMapping("/userCommentList")
    public ResultVo<List<Comment>> userCommentList(@RequestParam("buyerid") Integer buyerid){
        List<Comment> all =commentRepository.findAllByBuyerId(buyerid);
        return ResultVOUtil.success(all);
    }
}
