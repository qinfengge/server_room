package xyz.qinfengge.serversp.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import xyz.qinfengge.serversp.entity.Mois;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.qinfengge.serversp.entity.Ups;

import java.util.List;

/**
 * @Entity xyz.qinfengge.serversp.entity.Mois
 */
public interface MoisMapper extends BaseMapper<Mois> {
    Page<Mois> findMoisData(Page<Mois> page, @Param("d_name") String d_name);
    List<Mois> echarsShi(@Param("did") Integer did);
}




