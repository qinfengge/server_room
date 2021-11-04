package xyz.qinfengge.serversp.contoller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import xyz.qinfengge.serversp.common.Result;
import xyz.qinfengge.serversp.contoller.dto.UserRoleCountDto;
import xyz.qinfengge.serversp.entity.User;
import xyz.qinfengge.serversp.mapper.UserMapper;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lizhiao
 * @version 1.0
 * @date 2021/10/15 15:43
 */
@RestController
@RequestMapping("/user")
@ResponseBody
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/list")
    public Result<?> getUserList(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "5") Integer pageSize,
                                 @RequestParam(defaultValue = "") String search){
        Page page = new Page<>(pageNum,pageSize);
        Page<User> userPage = userMapper.selectPage(page, Wrappers.<User>lambdaQuery().like(User::getUsername,search));
        return Result.success(userPage);
    }

    @PostMapping("/save")
    public Result<?> saveUser(@RequestBody User user){
        /**
         * 设置新增用户权限为2,普通用户
         */
        user.setRole(2);
        userMapper.insert(user);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<?> updateUser(@RequestBody User user){
        userMapper.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> deleteUser(@PathVariable("id") Integer id){
        userMapper.deleteById(id);
        return Result.success();
    }
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user, HttpServletResponse response){
        User user1 = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPasswd, user.getPasswd()));
        if (user1==null){
            return Result.error("-1","用户名或密码错误!");
        }
        Cookie cookie = new Cookie("username", user1.getUsername());
        cookie.setPath("/");
        cookie.setMaxAge(1440000);
        response.addCookie(cookie);
        return Result.success(user1);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        User user1 = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (user1!=null){
            return Result.error("-1","用户名已存在!");
        }
        /**
         * 设置注册用户默认信息
         */
        user.setAge(18);
        user.setAddress("青青大草原");
        user.setSex("保密");
        user.setPhone("保密");
        user.setRole(2);
        user.setPic("http://localhost:8181/file/download/defaultPic.jpeg");
        userMapper.insert(user);
        return Result.success();
    }

    @GetMapping("/checkUserRole/{id}")
    public Result<?> checkUserRole(@PathVariable("id") Integer id){
        return Result.success(userMapper.selectById(id));
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        userMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/getRoleCount")
    public Result<?> getRoleCount(){
       List<UserRoleCountDto> urd = userMapper.selectRoleCount();
        for (UserRoleCountDto dto : urd) {
            if (dto.getRole()==1){
                dto.setRoleEx("管理员");
            }else if (dto.getRole()==2){
                dto.setRoleEx("普通用户");
            }
        }
        System.out.println(urd);
        return Result.success(urd);
    }
}
