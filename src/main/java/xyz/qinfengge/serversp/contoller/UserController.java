package xyz.qinfengge.serversp.contoller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import xyz.qinfengge.serversp.common.Result;
import xyz.qinfengge.serversp.contoller.dto.UserRoleCountDto;
import xyz.qinfengge.serversp.entity.User;
import xyz.qinfengge.serversp.mapper.UserMapper;
import xyz.qinfengge.serversp.util.JwtUtil;

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
        User userN = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword()));
        User userE = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getEmail,user.getUsername()).eq(User::getPassword, user.getPassword()));
        if (userN==null&&userE==null){
            return Result.error("-1","用户名或密码错误!");
        }else if (userN==null&&userE!=null){
            Cookie cookie = new Cookie("username", userE.getUsername());
            cookie.setPath("/");
            cookie.setMaxAge(1440000);
            response.addCookie(cookie);
            //JWT生成token
            userE.setToken(JwtUtil.generateToken(userE));
            //根据邮箱获取QQ头像
            if (userE.getPic()==null||userE.getPic()==""){
                String email = userE.getEmail();
                String[] emails = email.split("@");
                if (emails[1].equals("qq.com")){
                    userE.setPic("http://q2.qlogo.cn/headimg_dl?dst_uin=" + emails[0] + "&spec=640");
                }
            }
            //更新数据库
            userMapper.updateById(userE);
            return Result.success(userE);
        } else if (userN!=null&&userE==null) {
            Cookie cookie = new Cookie("username", userN.getUsername());
            cookie.setPath("/");
            cookie.setMaxAge(1440000);
            response.addCookie(cookie);
            //JWT生成token
            userN.setToken(JwtUtil.generateToken(userN));
            if (userN.getPic()==null||userN.getPic()==""){
                if(userN.getEmail()!=null || userN.getEmail()!=""){
                    String email = userN.getEmail();
                    String[] emails = email.split("@");
                    if (emails[1].equals("qq.com")){
                        userN.setPic("http://q2.qlogo.cn/headimg_dl?dst_uin=" + emails[0] + "&spec=640");
                    }
                }
            }
            //更新数据库
            userMapper.updateById(userN);
            return Result.success(userN);
        }else {
            Cookie cookie = new Cookie("username", userN.getUsername());
            cookie.setPath("/");
            cookie.setMaxAge(1440000);
            response.addCookie(cookie);
            //JWT生成token
            userN.setToken(JwtUtil.generateToken(userN));
            if (userN.getPic()==null||userN.getPic()==""){
                if(userN.getEmail()!=null || userN.getEmail()!=""){
                    String email = userN.getEmail();
                    String[] emails = email.split("@");
                    if (emails[1].equals("qq.com")){
                        userN.setPic("http://q2.qlogo.cn/headimg_dl?dst_uin=" + emails[0] + "&spec=640");
                    }
                }
            }
            //更新数据库
            userMapper.updateById(userN);
            return Result.success(userN);
        }

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
        String email = user.getEmail();
        String[] emails = email.split("@");
        if (emails[1].equals("qq.com")){
            user.setPic("http://q2.qlogo.cn/headimg_dl?dst_uin=" + emails[0] + "&spec=640");
        }else {
            user.setPic("http://localhost:8181/file/download/defaultPic.jpeg");
        }
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

    @PostMapping("/checkToken")
    public Boolean checkToken(@RequestBody User user){
        return JwtUtil.checkToken(user.getToken());
    }
}
