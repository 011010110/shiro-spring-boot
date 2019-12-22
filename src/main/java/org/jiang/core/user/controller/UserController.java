package org.jiang.core.user.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jiang.common.Result;
import org.jiang.core.user.bean.User;
import org.jiang.core.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author Li.Linhua
 * @since 2019-07-15
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * @description 列表
     * @param model
     * @param page
     * @return {@link String}
     * @author Li.Linhua
     * @date 2019/7/18
     */
    @RequestMapping("list")
    public String list(Model model, Page page){

        IPage pages = userService.page(page);
        List dataList = pages.getRecords();
        model.addAttribute("pages",pages);
        model.addAttribute("dataList",dataList);
        return "core/user/user_list";
    }


    /**
     * @description 编辑
     * @param id
     * @return {@link String}
     * @author Li.Linhua
     * @date 2019/7/18
     */
    @RequestMapping("edit")
    public String edit(@RequestParam String id, Model model){

        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "core/user/user_edit";
    }

    /**
     * @description 添加
     * @param
     * @return {@link String}
     * @author Li.Linhua
     * @date 2019/7/19
     */
    @RequestMapping("add")
    public String add(){
        return "core/user/user_edit";
    }

    /**
     * @description 查看
     * @param id
     * @return {@link String}
     * @author Li.Linhua
     * @date 2019/7/18
     */
    @RequestMapping("view")
    public String view(String id){
        return "core/user/user_view";
    }

    /**
     * @description 保存
     * @param user
     * @return {@link ResponseEntity< Map< String, Object>>}
     * @author Li.Linhua
     * @date 2019/7/18
     */
    @RequestMapping("save")
    @ResponseBody
    public ResponseEntity<Result> save(User user){
        Map<String,Object> data = new HashMap<>();
        data.put("name","hello");
        Result result = Result.success().setData(data).setMsg("保存成功");
        return ResponseEntity.ok().body(result);
    }

    /**
     * @description 修改
     * @param user
     * @return {@link Result}
     * @author Li.Linhua
     * @date 2019/7/18
     */
    @RequestMapping("update")
    @ResponseBody
    public Result update(User user){
        return Result.success().setData(user).setMsg("修改成功");
    }

    /**
     * @description 删除
     * @param id
     * @return {@link Result}
     * @author Li.Linhua
     * @date 2019/7/18
     */
    @RequestMapping("delete")
    @ResponseBody
    public Result delete(String id){
        return Result.success().setMsg("删除成功");
    }
}
