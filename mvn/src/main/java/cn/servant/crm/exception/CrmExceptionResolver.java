package cn.servant.crm.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 */
public class CrmExceptionResolver implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        // 如果抛出的是系统自定义异常类，则直接进行强转
        Exception exception;
        if (e instanceof CustomerException) {
            exception = (CustomerException) e;
        }else if(e instanceof LinkmanException){
            exception = (LinkmanException) e;
        }else{
            e.printStackTrace();
            // 如果不属于某个明确的系统自定义异常类，则抛出一个系统自定义的未知异常类
//            exception = new UnknownException("未知异常");
            exception = e;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
