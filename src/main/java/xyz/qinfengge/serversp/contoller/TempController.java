package xyz.qinfengge.serversp.contoller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import xyz.qinfengge.serversp.common.Result;
import xyz.qinfengge.serversp.entity.Temp;
import xyz.qinfengge.serversp.mapper.TempMapper;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lizhiao
 * @version 1.0
 * @date 2021/10/16 17:31
 */
@RestController
@RequestMapping("/temp")
@ResponseBody
public class TempController {

    @Resource
    TempMapper tempMapper;

    @PostMapping("/add")
    public Result<?> addTemp(@RequestBody Temp temp){
        temp.setCreatTime(new Date());
        tempMapper.insert(temp);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<?> updateTemp(@RequestBody Temp temp){
        tempMapper.updateById(temp);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> deleteTemp(@PathVariable("id") Integer id){
        tempMapper.deleteById(id);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        tempMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<?> findTempData(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                  @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Temp> wrapper = Wrappers.<Temp>lambdaQuery();
        Page<Temp> tempPage = tempMapper.findTempData(new Page<>(pageNum,pageSize),search);
        return Result.success(tempPage);
    }

    @GetMapping("/showEchars/{did}")
    public Result<?> showEchars(@PathVariable("did") Integer did){
        return Result.success(tempMapper.echarsWen(did));
    }

    @GetMapping("/warn")
    public Result<?> warnTemp(){
        return Result.success(tempMapper.warnTemp());
    }
}
