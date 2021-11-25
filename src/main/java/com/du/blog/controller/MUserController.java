package com.du.blog.controller;


import com.du.blog.mapper.MUserMapper;
import com.du.blog.response.CommonResult;
import com.du.blog.service.IMUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Du425
 * @since 2021-11-25
 */
@RestController
@RequestMapping("/mUser")
public class MUserController {

    @Autowired
    IMUserService imUserService;
    @Autowired
    MUserMapper mUserMapper;

    @GetMapping("/index")
   public CommonResult index(){
        if (mUserMapper.selectById(1L)==null){
            return CommonResult.failed("查询失败");
        }else {
            return CommonResult.success("查询成功",mUserMapper.selectById(1L));
        }
    }


}

