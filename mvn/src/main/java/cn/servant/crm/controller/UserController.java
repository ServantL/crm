package cn.servant.crm.controller;

import cn.servant.crm.pojo.po.User;
import cn.servant.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/editUser")
    public String editUser(){
        return "edituser";
    }

    @RequestMapping("/updateUserPassword")
    public String updateUserPassword(HttpSession session,User user) throws Exception {
        // 进行修改并获取更新后的user
        User updateUser = userService.updateUserPassword(user);
        // 对session中的user进行更新
        session.setAttribute("user", updateUser);
        return "success";
    }
}
