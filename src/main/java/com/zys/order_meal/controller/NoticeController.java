package com.zys.order_meal.controller;

import com.zys.order_meal.entity.Notice;
import com.zys.order_meal.exception.SellException;
import com.zys.order_meal.form.NoticeForm;
import com.zys.order_meal.repository.NoticeRepository;
import com.zys.order_meal.utils.ResultVOUtil;
import com.zys.order_meal.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController/* @Controller + @ResponseBody*/
@RequestMapping("/notice")
@Slf4j
public class NoticeController {
    @Autowired
    private NoticeRepository noticeRepository;

    /**
     * 卖家端请求所有公告列表
     * @param map
     * @return
     */
    @GetMapping("/NoticeList")
    public ModelAndView noticeList(Map<String , Object> map){
        List<Notice> notices = noticeRepository.findAll();
        map.put("notices",notices);
        return new ModelAndView("notice/list", map);
    }

    /**
     * 卖家查看公告详情
     * @param noticeId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("noticeId") Integer noticeId ,
                               Map<String , Object> map){
        Optional<Notice> noticeDetail = noticeRepository.findById(noticeId);
        map.put("noticeDetail",noticeDetail.get());
        return new ModelAndView("notice/detail", map);
    }

    /**
     * 卖家删除公告
     * @param noticeId
     * @param map
     * @return
     */
    @GetMapping("delete")
    public ModelAndView delete(@RequestParam("noticeId") Integer noticeId ,
                               Map<String , Object> map){
        noticeRepository.deleteById(noticeId);
        map.put("msg" , "公告删除成功！");
        map.put("url" , "/sell/notice/NoticeList");
        return new ModelAndView("common/success" , map);
    }

    /**
     * 跳转至编辑页面
     * @param noticeId
     * @param map
     * @return
     */
    @GetMapping("/edit")
    public ModelAndView editNotice(@RequestParam(value = "noticeId" , required = false) Integer noticeId ,
                              Map<String , Object> map){
        if(noticeId != null){
            Optional<Notice> notice = noticeRepository.findById(noticeId);
            map.put("notice",notice.get());
        }
        return new ModelAndView("notice/edit" , map);
    }

    /**
     * 保存编辑后的公告信息
     * @param noticeForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid NoticeForm noticeForm,
                             BindingResult bindingResult,
                             Map<String  , Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg" , bindingResult.getFieldError().getDefaultMessage());
            map.put("url" , "/sell/notice/edit");
            return new ModelAndView("common/error" , map);
        }
        try{
            //不为空则为修改
            if(noticeForm.getNoticeId() != null){
                Optional<Notice> notice = noticeRepository.findById(noticeForm.getNoticeId());
                BeanUtils.copyProperties(noticeForm , notice.get());
                noticeRepository.save(notice.get());
            }
            else{
                //为空则为新建
                Notice notice = new Notice();
                BeanUtils.copyProperties(noticeForm, notice);
                noticeRepository.save(notice);
            }
        } catch (SellException e){
            map.put("msg" , e.getMessage());
            map.put("url" , "/sell/notice/edit");
            return new ModelAndView("common/error" , map);
        }
        map.put("msg" , "公告操作成功！");
        map.put("url" , "/sell/notice/NoticeList");
        return new ModelAndView("common/success" , map);
    }

    /**
     * 买家请求所有公告
     * @return
     */
    @GetMapping("/allNotice")
    public ResultVo<List<Notice>> queryAllNotice(){
        List<Notice> allNotice = noticeRepository.findAll();
        return ResultVOUtil.success(allNotice);
    }

    /**
     * 买家查看某一公告
     * @param noticeId
     * @return
     */
    @GetMapping("/oneNotice")
    public ResultVo Notice(@RequestParam("noticeId") Integer noticeId){
        Optional<Notice> notice = noticeRepository.findById(noticeId);
        return ResultVOUtil.success(notice.get());
    }
}
