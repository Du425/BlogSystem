package com.du.blog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.du.blog.dto.LoginDto;
import com.du.blog.entity.MUser;
import com.du.blog.response.CommonResult;
import com.du.blog.service.IMUserService;
import com.du.blog.shiro.JwtFilter;
import com.du.blog.util.JwtUtil;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;

@RestController
public class AccountController {

    @Autowired
    IMUserService imUserService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public CommonResult login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){

        MUser user = imUserService.getOne(new UpdateWrapper<MUser>().eq("username", loginDto.getUsername()));
        Assert.notNull(user,"用户不存在");
        //判断账号密码是否错误 因为是md5加密所以这里md5判断
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            //密码不同则抛出异常
            return CommonResult.failed("密码不正确");
        }

        String jwt = jwtUtil.generateToken(user.getId());
//将token 放在我们的header里面
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");

        return CommonResult.success(MapUtil.builder()
                .put("id",user.getId())
                .put("username",user.getUsername())
                .put("avatar",user.getAvatar())
                .put("email",user.getEmail())
                .map()
        );
    }
    //需要认证权限才能退出登录
    @RequiresAuthentication
    @GetMapping("/logout")
    public CommonResult logout(){
        SecurityUtils.getSubject().logout();
        return CommonResult.success(null);
    }
}
