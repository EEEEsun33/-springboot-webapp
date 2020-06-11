package com.zys.order_meal.controller;

import com.zys.order_meal.entity.BuyerInfo;
import com.zys.order_meal.repository.BuyerInfoRepository;
import com.zys.order_meal.service.UserService;
import com.zys.order_meal.utils.JwtUtils;
import com.zys.order_meal.utils.KeyUtil;
import com.zys.order_meal.utils.ResultVOUtil;
import com.zys.order_meal.vo.ResultVo;
import com.zys.order_meal.vo.UserVo;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户的controller层
 */
@RestController
@RequestMapping("/buyer")
@Slf4j
public class UsersController {
    //    注入实体类
    @Autowired
    private BuyerInfoRepository buyerInfoRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @RequestMapping("/register")
    public ResultVo BuyerRegister(@RequestParam(value = "username") String username, @RequestParam(value = "phone") String phone, @RequestParam(value = "password") String password) {

        BuyerInfo buyerInfo = new BuyerInfo();
        Integer buyerKey = KeyUtil.buyerKey();

        buyerInfo.setUsername(username+"#"+buyerKey);
        buyerInfo.setPhone(phone);
        buyerInfo.setPassword(password);
        Map<String, Object> dataMap = new HashMap<>();
        String token = jwtUtils.createJwt(buyerInfo.getPhone(), buyerInfo.getUsername(),dataMap);
        userService.BuyerRegister(buyerInfo);

        ArrayList<UserVo> userVos = new ArrayList<>();
        UserVo userVo = new UserVo();
        userVo.setBuyerInfo(buyerInfo);
        userVo.setToken(token);
        userVos.add(userVo);
        return ResultVOUtil.success(userVos);
//        return new ResponseEntity<> ( buyerInfo, HttpStatus.OK );
    }

    @RequestMapping("/login")
    public ResultVo Login(HttpServletRequest request, HttpSession session,
                          @RequestParam(value = "phone") String phone,
                          @RequestParam(value = "password") String password) {
        if (userService.BuyerLogin(phone,password)){
            System.out.println("登录成功");
            BuyerInfo buyerInfo = buyerInfoRepository.findByPhone(phone);
            session.setAttribute("loginuser",phone);

            //其他数据以map集合存放在token中
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("buyerPhone", buyerInfo.getPhone());
//            dataMap.put("buyerPassword", buyerInfo.getPassword());
            dataMap.put("buyerId",buyerInfo.getBuyerId());
            //生成token并存入数据返回
            String token = jwtUtils.createJwt(buyerInfo.getPhone(), buyerInfo.getUsername(), dataMap);

            ArrayList<UserVo> userVos = new ArrayList<>();
            UserVo userVo = new UserVo();
            userVo.setBuyerInfo(buyerInfo);
            userVo.setToken(token);
            userVos.add(userVo);
            return ResultVOUtil.success(userVos);

        }else {
            System.out.println("登录失败");
            return ResultVOUtil.error(400,"登录失败，原因未知");
        }

    }

    @GetMapping("/getBuyerInfo")
    public ResultVo getBuyerInfo(@RequestParam(value = "buyerId") Integer buyerId){
        BuyerInfo buyerInfo = buyerInfoRepository.findByBuyerId(buyerId);
        return ResultVOUtil.success(buyerInfo);
    }

    @GetMapping("/getToken")
    public ResultVo getToken(HttpServletRequest request){
        String token=request.getHeader("token");
        Claims claims = jwtUtils.parseJwt(token);
        return  ResultVOUtil.success(claims);
    }
}
