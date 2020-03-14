package org.jiang.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jiang.common.Result;
import org.jiang.core.user.bean.User;
import org.jiang.core.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Li.Linhua
 * @description 用户接口
 * @Date 2019/7/19
 */
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserService userService;

    /**
     * @description 用户list
     * @param model
     * @param page
     * @return {@link Result}
     * @author Li.Linhua
     * @date 2019/7/19
     */
    @RequestMapping("/list")
    public Result list(Model model, Page page){

        IPage pages = userService.page(page);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("pages",pages);
        return Result.success().setData(pages);
    }

    /**
     * @description 添加用户
     * @param user
     * @return {@link Result}
     * @author Li.Linhua
     * @date 2019/7/19
     */
    @RequestMapping("/save")
    public Result save(@Validated User user){

        try{
            boolean result = userService.encryptPasswordSave(user);
            if(result){
                return Result.success().setMsg("保存成功");
            }
        }catch (Exception e){
            logger.info("保存用户失败");
            return Result.error().setMsg("保存失败");
        }
        return Result.error().setMsg("保存失败");
    }

    /**
     * @description 删除
     * @param ids
     * @return {@link Result}
     * @author Li.Linhua
     * @date 2019/7/19
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody List<String> ids){

        try{
            boolean result = userService.removeByIds(ids);
            if(result){
                return Result.success().setMsg("操作成功");
            }
        }catch (Exception e){
            logger.info("删除用户失败");
            return Result.error().setMsg("删除失败");
        }
        return Result.error().setMsg("删除失败");
    }

    /**
     * @description 更新
     * @param user
     * @return {@link Result}
     * @author Li.Linhua
     * @date 2019/7/19
     */
    @RequestMapping("/update")
    public Result update(User user){
        try{
            boolean result = userService.updateById(user);
            if(result){
                return Result.success().setMsg("操作成功");
            }
        }catch (Exception e){
            logger.error("更新用户错误");
            return Result.error().setMsg("更新用户信息失败");
        }
        return Result.error().setMsg("操作失败了");
    }

    /**
     * @description 查看用户信息
     * @param id
     * @return {@link org.jiang.common.Result}
     * @author Li.Linhua
     * @date 2019/7/19
     */
    @RequestMapping("/{id}")
    public Result get(@PathVariable("id") String id){
        try {
            User user = userService.getById(id);
            if(user!=null){
                return Result.success().setMsg("操作成功").setData(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取用户信息错误");
            return Result.error().setMsg("获取用户信息失败");
        }
        return Result.error().setMsg("操作失败");
    }
}
