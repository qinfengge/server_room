package xyz.qinfengge.serversp.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import xyz.qinfengge.serversp.entity.Ups;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity xyz.qinfengge.serversp.entity.Ups
 */
public interface UpsMapper extends BaseMapper<Ups> {
    Page<Ups> findUpsData(Page<Ups> page, @Param("d_name") String d_name);
    List<Ups> echarsUps(@Param("did") Integer did);
}




