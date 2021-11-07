package xyz.qinfengge.serversp.mapper;

import org.apache.ibatis.annotations.Update;
import xyz.qinfengge.serversp.entity.Msg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity xyz.qinfengge.serversp.entity.Msg
 */
public interface MsgMapper extends BaseMapper<Msg> {
    void insIg(Msg msg);

    //清空指定表
    @Update("truncate table msg")
    void clearAll();
}




