package com.du.blog.controller;


import cn.hutool.crypto.SecureUtil;
import com.du.blog.entity.MUser;
import com.du.blog.mapper.MUserMapper;
import com.du.blog.response.CommonResult;
import com.du.blog.service.IMUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

//    @RequiresAuthentication
    @GetMapping("/index")
   public CommonResult index(){
//        if (mUserMapper.selectById(1L)==null){
//            return CommonResult.failed("查询失败");
//        }else {
//            return CommonResult.success("查询成功",mUserMapper.selectById(1L));
//        }
        MUser byId = imUserService.getById(1L);
        return CommonResult.success(byId);


    }

    @PostMapping("/save")
    public CommonResult save(@Validated @RequestBody MUser mUser){
        String rawPassword = mUser.getPassword();
        String password = SecureUtil.md5(rawPassword);
        SecureUtil.md5(password);
        mUser.setPassword(password);
        int insert = mUserMapper.insert(mUser);
        if (insert==1){
            return CommonResult.success("插入成功",mUser);
        }else {
            return CommonResult.failed("插入失败");
        }

    }




}

