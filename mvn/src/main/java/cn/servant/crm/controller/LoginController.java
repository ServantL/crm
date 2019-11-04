package cn.servant.crm.controller;

import cn.servant.crm.pojo.po.User;
import cn.servant.crm.service.LinkmanService;
import cn.servant.crm.service.UserService;
import cn.servant.crm.utils.IdentifyCodeImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.*;

@Controller
//@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LinkmanService linkmanService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response, Model model, @Validated User user, BindingResult bindingResult, String identifyCode) throws Exception {
        // 判断是否有校验错误信息
//        if (bindingResult.hasErrors()) {
//            List<ObjectError> messageErrors = bindingResult.getAllErrors();
//            Map<String, List> messageErrorsMap = new HashMap<>();
//            List<String> messageErrorsList = new ArrayList<>();
//            messageErrorsMap.put("messageErrors", messageErrorsList);
//            for (int i = 0; i < messageErrors.size(); i++) {
//                messageErrorsList.add(messageErrors.get(i).getDefaultMessage());
//            }
//            // 出现错误将错误信息回显到页面中
////            model.addAttribute("messageErrors", messageErrors);
//            return messageErrors;
//        }
        HttpSession httpSession = request.getSession();
//        Map<String, String> loginMessage = new HashMap<String, String>();
//        model.addAttribute("loginMessage", loginMessage);
        String code = httpSession.getAttribute("identifyCode").toString();
        if (!identifyCode.equalsIgnoreCase(code)) {
            // 添加提示信息
//            loginMessage.put("identifyCodeMessage", "验证码错误");
//            return loginMessage;
            return "identifyCodeMessage";
        }
        User loginUser = userService.selectUserByAccountAndPassword(user);
        if (loginUser == null || loginUser.getUserId() == null) {
            // 添加提示信息
//            loginMessage.put("userMessage", "用户名或密码错误");
//            return loginMessage;
            return "userMessage";
        }

        // 登录成功
        // 将用户存储在session中，后面可进行权限操作
        httpSession.setAttribute("user", loginUser);
        if (loginUser.getUserRights() == 1) {
            // 如果登录的为管理员用户
            httpSession.setAttribute("username", "管理员");
        } else if (loginUser.getUserRights() == 2) {
            httpSession.setAttribute("username", loginUser.getUserLinkman().getLinkName());

            // 如果登录的为联系人用户，则联合查询联系人的信息
            // 在用户表中加一个字段，或者在联系人表中加一个字段，待考虑
            // 因为在用户表中加字段但是管理员不需要有对应的信息，则管理那则为空
            // 在联系人那则需要用到联系人对应的service，累赘？
        }
//        String redirect = request.getHeader("Referer");
//        response.sendRedirect(request.getContextPath());
        return "success";
//        if (redirect.contains("logout")) {
//            response.sendRedirect("/welcome.htm");
//        }
//        String contextPath = request.getContextPath();
//        response.sendRedirect("redirect:" + redirect.substring(redirect.indexOf(contextPath) + contextPath.length()));
    }

    @RequestMapping("/identifyCode")
    @ResponseBody
    public void identifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取验证码以及验证码图片
        HashMap hashMap = IdentifyCodeImageUtil.getIdentifyCode();
        // 获取验证码
        String identifyCode = (String) hashMap.get("identifyCode");
        HttpSession httpSession = request.getSession();
        // 将该验证码存放到session中，在用户登录时用于校验
        httpSession.setAttribute("identifyCode", identifyCode);
        String sessionId = httpSession.getId();
        // 获取验证码图片
        BufferedImage bufferedImage = (BufferedImage) hashMap.get("identifyCodeImage");
        // 将验证码图片画到客户端中
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
//        return bufferedImage;
//        org.springframework.http.converter.HttpMessageNotWritableException: No converter found for return value of type: class java.awt.image.BufferedImage
    }

    @RequestMapping("/logout")
    @ResponseBody
    public void logout(HttpSession session) {
        session.invalidate();
//        return "login";
    }


}
