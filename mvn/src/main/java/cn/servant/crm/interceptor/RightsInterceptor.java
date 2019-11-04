package cn.servant.crm.interceptor;

import cn.servant.crm.pojo.po.CustomerBasic;
import cn.servant.crm.pojo.po.Linkman;
import cn.servant.crm.pojo.po.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Parameter;
import java.util.List;

public class RightsInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 公开页面
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/list")) {
            return true;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // 权限为1，即为管理员用户，则直接放行
        if (user.getUserRights() == 1) {
            return true;
        }

        String linkId = request.getParameter("linkId");
        if (linkId != null && linkId.equals(user.getUserLinkman().getLinkId().toString()) && (requestURI.contains("/edit") || requestURI.contains("/update"))) {
            return true;
        }

        // 对修改和删除客户进行拦截，只有是当前联系人用户的门下客户才能进行相应的操作
        // 添加则不用拦截，毕竟每个联系人都有添加用户的权限
        if (!requestURI.contains("/linkman")) {
            String custId = request.getParameter("custId");
            List<CustomerBasic> list = user.getUserLinkman().getLinkCustomerList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCustId().toString().equals(custId)) {
                    return true;
                }
            }
        }
        // 权限不足
//        String contextPath = request.getContextPath();
//        response.sendRedirect("/jsp/rights.jsp");
        request.getRequestDispatcher("/jsp/rights.jsp").forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
