package cn.servant.crm.interceptor;

import cn.servant.crm.pojo.po.Linkman;
import cn.servant.crm.pojo.po.User;
import cn.servant.crm.service.LinkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateSessionInterceptor implements HandlerInterceptor{
    @Autowired
    private LinkmanService linkmanService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getUserLinkman() == null) {
            // 则为管理员，不需要任何更新session的操作
            return;
        }
        Linkman linkman = linkmanService.selectLinkmanById(user.getUserLinkman().getLinkId());
        user.setUserLinkman(linkman);
        session.setAttribute("username", linkman.getLinkName());
    }
}
