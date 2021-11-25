package com.du.blog.service.impl;

import com.du.blog.entity.MBlog;
import com.du.blog.mapper.MBlogMapper;
import com.du.blog.service.IMBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Du425
 * @since 2021-11-25
 */
@Service
public class MBlogServiceImpl extends ServiceImpl<MBlogMapper, MBlog> implements IMBlogService {

}
