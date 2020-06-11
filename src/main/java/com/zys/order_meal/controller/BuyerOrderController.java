package com.zys.order_meal.controller;

import com.zys.order_meal.converter.OrderForm2OrderDTOConverter;
import com.zys.order_meal.dto.OrderDTO;
import com.zys.order_meal.enums.ResultEnum;
import com.zys.order_meal.exception.SellException;
import com.zys.order_meal.form.OrderForm;
import com.zys.order_meal.service.BuyerService;
import com.zys.order_meal.service.OrderService;
import com.zys.order_meal.utils.ResultVOUtil;
import com.zys.order_meal.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家订单的Controller层
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm , BindingResult bindingResult){

        //判断表单校验有无错误
        if(bindingResult.hasErrors()){
            log.error("【创建订单】 参数不正确 , orderForm={}" , orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode() ,
                    bindingResult.getFieldError().getDefaultMessage());
        }

        // 将表单信息转换成dto类型
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.conver(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId" , createResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    //查询买家个人订单
    @GetMapping("/list")
    public ResultVo list(@RequestParam("buyerid") Integer buyerid ,
                         @RequestParam(value = "page" , defaultValue = "0") Integer page ,
                         @RequestParam(value = "size" , defaultValue = "5") Integer size){
        if(StringUtils.isEmpty(buyerid)){
            log.error("【查询订单列表】 buyerid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page , size);
        Page<OrderDTO> orderDTOPage = orderService.findList(buyerid, request);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    //家根据订单状态查询订单
    @GetMapping("/listByStatus")
    public ResultVo<List<OrderDTO>> listByStatus(@RequestParam("buyerid") Integer buyerid ,
                                                 @RequestParam(value="orderStatus" , defaultValue = "0") Integer orderStatus){
        if(StringUtils.isEmpty(buyerid)){
            log.error("【查询订单列表】buyerid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        List<OrderDTO> orderList = buyerService.findOrderList(buyerid , orderStatus);
        return ResultVOUtil.success(orderList);
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(@RequestParam("buyerid") Integer buyerid ,
                                     @RequestParam("orderId") String orderId){
        OrderDTO orderDTO = buyerService.findOrderOne(buyerid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    @PostMapping("/finish")
    public ResultVo finish(@RequestParam("orderId") String orderId){
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        return ResultVOUtil.success();
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("buyerid") Integer buyerid ,
                           @RequestParam("orderId") String orderId){
        buyerService.cancelOrder(buyerid , orderId);
        return ResultVOUtil.success();
    }

}
