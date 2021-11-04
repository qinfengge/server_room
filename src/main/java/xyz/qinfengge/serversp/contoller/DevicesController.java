package xyz.qinfengge.serversp.contoller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import xyz.qinfengge.serversp.common.Result;
import xyz.qinfengge.serversp.contoller.dto.DevicesStatusDto;
import xyz.qinfengge.serversp.entity.Devices;
import xyz.qinfengge.serversp.mapper.DevicesMapper;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lizhiao
 * @version 1.0
 * @date 2021/10/15 20:36
 */
@RestController
@ResponseBody
@RequestMapping("/devices")
public class DevicesController {

    @Resource
    DevicesMapper devicesMapper;

    @PostMapping("add")
    public Result<?> addDevices(@RequestBody Devices devices){
        devices.setStatus(1);
        devices.setCreateTime(new Date());
        devicesMapper.insert(devices);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<?> listDevices(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "5") Integer pageSize,
                                 @RequestParam(defaultValue = "") String search){
        Page page = new Page<>(pageNum,pageSize);
        Page<Devices> devicesPage = devicesMapper.selectPage(page, Wrappers.<Devices>lambdaQuery().like(Devices::getDname,search));
        return Result.success(devicesPage);
    }

    @PutMapping("/update")
    public Result<?> updateDevices(@RequestBody Devices devices){
        devicesMapper.updateById(devices);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> deleteDevices(@PathVariable("id") Integer id){
        devicesMapper.deleteById(id);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatchDevices(@RequestBody List<Integer> ids){
        devicesMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/devicesStatusCount")
    public Result<?> devicesStatusCount(){
        List<DevicesStatusDto> dsd = devicesMapper.devicesStatusCount();
        for (DevicesStatusDto dto : dsd) {
            if (dto.getStatus()==1){
                dto.setStatusEx("正常");
            }else if (dto.getStatus()==0){
                dto.setStatusEx("异常");
            }
        }
        return Result.success(dsd);
    }
    @GetMapping("/typeList/{type}")
    public Result<?> typeList(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "5") Integer pageSize,
                                 @RequestParam(defaultValue = "") String search,
                              @PathVariable("type") Integer type){
        Page page = new Page<>(pageNum,pageSize);
        Page<Devices> devicesPage = devicesMapper.selectPage(page, Wrappers.<Devices>lambdaQuery().like(Devices::getDname,search).eq(Devices::getType,type));
        return Result.success(devicesPage);
    }
}
