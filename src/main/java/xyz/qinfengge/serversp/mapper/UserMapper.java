package xyz.qinfengge.serversp.mapper;

import org.apache.ibatis.annotations.Select;
import xyz.qinfengge.serversp.common.Result;
import xyz.qinfengge.serversp.contoller.dto.UserRoleCountDto;
import xyz.qinfengge.serversp.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity xyz.qinfengge.serversp.entity.User
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select count(1) count,role from user  GROUP BY role")
    List<UserRoleCountDto> selectRoleCount ();
}




