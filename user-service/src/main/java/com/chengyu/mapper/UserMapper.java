package com.chengyu.mapper;

import com.chengyu.model.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户  Mapper 接口
 * </p>
 *
 * @author Lix
 * @since 2021-03-10
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

}
