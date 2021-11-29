package com.du.blog.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.du.blog.entity.MUser;
import com.du.blog.mapper.MUserMapper;
import com.du.blog.response.CommonResult;
import com.du.blog.service.IMUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        MUser byId = imUserService.getById(1L);
        return CommonResult.success(byId);
    }

    @PostMapping("/insert")
    public CommonResult insert(@Validated @RequestBody MUser mUser){
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

    @DeleteMapping("/delete")
    public CommonResult delete(MUser mUser){
        QueryWrapper<MUser> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("username",mUser.getUsername())
                .or().eq("id",mUser.getId())
                .or().eq("email",mUser.getEmail());
        if (mUserMapper.delete(objectQueryWrapper)==1){
            return CommonResult.success("删除成功");
        }else {
            return CommonResult.success("删除失败");
        }
    }

    @GetMapping("/queryUserList")
    public CommonResult userList(MUser mUser){
        QueryWrapper<MUser> objectQueryWrapper = new QueryWrapper<>(null);
        List<MUser> userList = mUserMapper.selectList(objectQueryWrapper);
        for (MUser user : userList) {
            System.out.println(user);
        }
        return CommonResult.success(userList);
    }





}

