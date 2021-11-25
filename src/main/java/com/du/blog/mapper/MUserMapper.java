package com.du.blog.mapper;

import com.du.blog.entity.MUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Du425
 * @since 2021-11-25
 */
@Mapper
@Repository
public interface MUserMapper extends BaseMapper<MUser> {

}
