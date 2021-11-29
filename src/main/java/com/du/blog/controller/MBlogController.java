package com.du.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.du.blog.entity.MBlog;
import com.du.blog.response.CommonResult;
import com.du.blog.service.IMBlogService;
import com.du.blog.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Du425
 * @since 2021-11-25
 */
@RestController
@RequestMapping("/mBlog")
public class MBlogController {

        @Autowired
        IMBlogService imBlogService;

        @GetMapping("/blogs") //分页功能
        public CommonResult blogs(Integer currentPage) {
            if(currentPage == null || currentPage < 1) currentPage = 1;
            Page<MBlog> page = new Page<MBlog>(currentPage, 5);
            IPage<MBlog> pageData = imBlogService.page(page,new QueryWrapper<MBlog>().orderByDesc("created"));
            return  CommonResult.success(pageData);
        }
        @GetMapping("/queryById/{id}")
        public CommonResult detail(@PathVariable(name = "id") Long id) {
            MBlog blog = imBlogService.getById(id);
            Assert.notNull(blog, "该博客已删除！");
            return CommonResult.success(blog);
        }

    //@Validated校验
    //@RequestBody
    //添加删除  木有id则添加 有id则编辑
        @RequiresAuthentication //需要登录之后才能编辑
        @PostMapping("/edit")
        public CommonResult edit(@Validated @RequestBody MBlog mBlog) {
            System.out.println(mBlog.toString());
            MBlog temp = null;
            if(mBlog.getId() != null) {
                temp = imBlogService.getById(mBlog.getId());
                //只能编辑自己的文章
                Assert.isTrue(Objects.equals(temp.getUserId(), ShiroUtil.getProfile().getId()), "没有权限编辑");
            } else {
                temp = new MBlog();
                temp.setUserId(ShiroUtil.getProfile().getId());
                temp.setCreated(LocalDateTime.now());
                temp.setStatus(0);
            }
            BeanUtil.copyProperties(mBlog, temp, "id", "userId", "created", "status");
            imBlogService.saveOrUpdate(temp);
            return CommonResult.success("操作成功", null);
        }
}

