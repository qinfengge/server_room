package xyz.qinfengge.serversp.contoller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import xyz.qinfengge.serversp.common.Result;
import xyz.qinfengge.serversp.entity.Msg;
import xyz.qinfengge.serversp.entity.Temp;
import xyz.qinfengge.serversp.mapper.MsgMapper;
import xyz.qinfengge.serversp.mapper.TempMapper;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.HttpCookie;
import java.util.Date;
import java.util.List;

/**
 * @author lizhiao
 * @version 1.0
 * @date 2021/11/3 22:06
 */
@RestController
@ResponseBody
@RequestMapping("/msg")
public class MsgController {
    @Resource
    private MsgMapper msgMapper;

    @Resource
    private TempMapper tempMapper;

    @PostMapping("/add")
    public Result<?> addMsg(@RequestBody Msg msg){
        msgMapper.insert(msg);
        return Result.success();
    }

    @GetMapping("/addWarnMsg")
    public Result<?> addWarnMsg(HttpServletRequest request){
        List<Temp> temps = tempMapper.warnTemp();
        for (Temp temp : temps) {
            Msg msg = new Msg();
            msg.setMsg("设备"+temp.getDid()+"\n超出预警!!!!!"+"数据编号为"+temp.getId()+"\n温度为"+temp.getWenData()+"°C"+"\n创建时间为"+temp.getCreatTime());
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())){
                    msg.setUsername(cookie.getValue());
                }
            }
//            List<Msg> msgs = msgMapper.selectList(null);
//            for (Msg msg1 : msgs) {
//                if (msg1.getMsg().equals(msg.getMsg())){
//                    System.out.println(msg1.getMsg());
//                    System.out.println(msg.getMsg());
//                  continue;
//                }
//                break;
//            }
            msg.setDataTime(new Date());
            msgMapper.insIg(msg);
        }
        return Result.success();
    }

    @GetMapping("/list")
    public Result<?> queryMsg() {
        return Result.success(msgMapper.selectList(null));
    }

    @PutMapping("/isShow")
    public Result<?> updateMsg(@RequestBody Msg msg) {
        //设置弹窗已显示
        msg.setIsShow(1);
        msgMapper.updateById(msg);
        return Result.success();
    }

    @PutMapping("/clearAll")
    public Result<?> clearAll() {
        msgMapper.clearAll();
        return Result.success();
    }
}
