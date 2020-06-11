package com.zys.order_meal.controller;

import com.zys.order_meal.entity.MonthlySales;
import com.zys.order_meal.entity.Top5Buyer;
import com.zys.order_meal.repository.OrderDetailRepository;
import com.zys.order_meal.repository.OrderMasterRepository;
import com.zys.order_meal.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 统计模块的controller
 */
@Controller
@RequestMapping("/seller/statistics")
public class StatisticsController {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping("/index")
    public ModelAndView list(Map<String , Object> map) throws Exception {
        //月销售量前5的商品
        List<Object[]> objects = orderDetailRepository.queryByMonthlySales();
        List<MonthlySales> monthlySales = EntityUtils.castEntity(objects, MonthlySales.class);
        //月消费金额前5的顾客
        List<Object[]> buyers = orderMasterRepository.queryTop5();
        List<Top5Buyer> top5Buyers = EntityUtils.castEntity(buyers,Top5Buyer.class);

        map.put("monthlySales", monthlySales);
        map.put("top5Buyers",top5Buyers);
        return new ModelAndView("statistics/index", map);
    }

    @GetMapping("/test")
    @ResponseBody
    public List<MonthlySales> listTest() throws Exception {
        List<Object[]> objects = orderDetailRepository.queryByMonthlySales();
        List<MonthlySales> monthlySales = EntityUtils.castEntity(objects,MonthlySales.class);
        return monthlySales;
    }

    @GetMapping("/topTest")
    @ResponseBody
    public List<Top5Buyer> top5test() throws Exception {
        List<Object[]> objects = orderMasterRepository.queryTop5();
        List<Top5Buyer> top5Buyers = EntityUtils.castEntity(objects,Top5Buyer.class);
        return top5Buyers;
    }

}
