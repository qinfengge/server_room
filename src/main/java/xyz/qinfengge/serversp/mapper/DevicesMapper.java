package xyz.qinfengge.serversp.mapper;

import org.apache.ibatis.annotations.Select;
import xyz.qinfengge.serversp.contoller.dto.DevicesStatusDto;
import xyz.qinfengge.serversp.entity.Devices;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity xyz.qinfengge.serversp.entity.Devices
 */
public interface DevicesMapper extends BaseMapper<Devices> {
    @Select("select count(1) count,status from devices  GROUP BY status")
    List<DevicesStatusDto> devicesStatusCount();
}




