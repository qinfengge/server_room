package xyz.qinfengge.serversp.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import xyz.qinfengge.serversp.entity.Temp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity xyz.qinfengge.serversp.entity.Temp
 */
public interface TempMapper extends BaseMapper<Temp> {
    Page<Temp> findTempData(Page<Temp> page, @Param("d_name") String d_name);
    List<Temp> echarsWen(@Param("did") Integer did);
    List<Temp> warnTemp();
}




