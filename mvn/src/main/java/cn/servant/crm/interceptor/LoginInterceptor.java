package cn.servant.crm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        // 判断该地址是否为公开地址（此时公开地址假设只有登录界面），正常将所有公开地址放在一个文件中
        if (uri.lastIndexOf("login.action") >= 0 || uri.lastIndexOf("identifyCode.action") >= 0) {
            // 放行
            return true;
        }
        // 如果该地址不为公开，则判断用户是否已经登录了，判断session
        HttpSession httpSession = request.getSession();
        String username = (String) httpSession.getAttribute("username");
        if (username != null) {
            // 如果存在用户，则放行
            return true;
        }
        // 不存在则跳转到登录页面
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
