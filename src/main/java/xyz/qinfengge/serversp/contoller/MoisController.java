package xyz.qinfengge.serversp.contoller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import xyz.qinfengge.serversp.common.Result;
import xyz.qinfengge.serversp.entity.Mois;
import xyz.qinfengge.serversp.mapper.MoisMapper;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author lizhiao
 * @version 1.0
 * @date 2021/10/17 0:09
 */
@RestController
@ResponseBody
@RequestMapping("/mois")
public class MoisController {

    @Resource
    MoisMapper moisMapper;

    @PostMapping("/add")
    public Result<?> addUps(@RequestBody Mois mois){
        mois.setCreatTime(new Date());
        moisMapper.insert(mois);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<?> updateTemp(@RequestBody Mois mois){
        moisMapper.updateById(mois);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> deleteTemp(@PathVariable("id") Integer id){
        moisMapper.deleteById(id);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        moisMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<?> findTempData(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                  @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Mois> wrapper = Wrappers.<Mois>lambdaQuery();
        Page<Mois> moisPage = moisMapper.findMoisData(new Page<>(pageNum,pageSize),search);
        return Result.success(moisPage);
    }

    @GetMapping("/showEchars/{did}")
    public Result<?> showEchars(@PathVariable("did") Integer did){
        return Result.success(moisMapper.echarsShi(did));
    }
}
