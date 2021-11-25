package com.du.blog.service.impl;

import com.du.blog.entity.MUser;
import com.du.blog.mapper.MUserMapper;
import com.du.blog.service.IMUserService;
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
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements IMUserService {

}
