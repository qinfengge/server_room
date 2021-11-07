package xyz.qinfengge.serversp.contoller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import xyz.qinfengge.serversp.common.Result;
import xyz.qinfengge.serversp.entity.Warning;
import xyz.qinfengge.serversp.mapper.WarningMapper;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lizhiao
 * @version 1.0
 * @date 2021/10/28 17:47
 */
@RestController
@RequestMapping("/warning")
@ResponseBody
public class WarningController {

    @Resource
    private WarningMapper warningMapper;

    @PostMapping("add")
    public Result<?> addWarning(@RequestBody Warning warning){
        warning.setCreateTime(new Date());
        warningMapper.insert(warning);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<?> listWarning(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "5") Integer pageSize){
        Page page = new Page<>(pageNum,pageSize);
        Page<Warning> warningPage = warningMapper.selectPage(page, Wrappers.<Warning>lambdaQuery());
        return Result.success(warningPage);
    }

    @PutMapping("/update")
    public Result<?> updateWarning(@RequestBody Warning warning){
        warningMapper.updateById(warning);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> deleteWarning(@PathVariable("id") Integer id){
        warningMapper.deleteById(id);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatchDevices(@RequestBody List<Integer> ids){
        warningMapper.deleteBatchIds(ids);
        return Result.success();
    }
}
