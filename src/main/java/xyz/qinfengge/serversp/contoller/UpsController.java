package xyz.qinfengge.serversp.contoller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import xyz.qinfengge.serversp.common.Result;
import xyz.qinfengge.serversp.entity.Ups;
import xyz.qinfengge.serversp.mapper.UpsMapper;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lizhiao
 * @version 1.0
 * @date 2021/10/16 22:48
 */
@RestController
@ResponseBody
@RequestMapping("/ups")
public class UpsController {

    @Resource
    UpsMapper upsMapper;

    @PostMapping("/add")
    public Result<?> addUps(@RequestBody Ups ups){
        ups.setCreatTime(new Date());
        upsMapper.insert(ups);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<?> updateTemp(@RequestBody Ups ups){
        upsMapper.updateById(ups);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> deleteTemp(@PathVariable("id") Integer id){
        upsMapper.deleteById(id);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        upsMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<?> findTempData(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                  @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Ups> wrapper = Wrappers.<Ups>lambdaQuery();
        Page<Ups> upsPage = upsMapper.findUpsData(new Page<>(pageNum,pageSize),search);
        return Result.success(upsPage);
    }

    @GetMapping("/showEchars/{did}")
    public Result<?> showEchars(@PathVariable("did") Integer did){
        return Result.success(upsMapper.echarsUps(did));
    }
}
