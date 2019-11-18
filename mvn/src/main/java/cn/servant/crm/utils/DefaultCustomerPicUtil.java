package cn.servant.crm.utils;


import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DefaultCustomerPicUtil {

    /**
     * 根据名字产生该用户的默认图片，只包含名字的图片，
     * 用于客户没有选择图片时使用
     * @param customerName
     * @return
     */
    public static BufferedImage getDefaultCustomerPic(String customerName){
        int len = customerName.length();
        // 新建缓存图片
        BufferedImage bufferedImage = new BufferedImage(200,200,BufferedImage.TYPE_INT_BGR);
        // 获取画笔，图片的形成都是基于画笔去化的
        Graphics graphics = bufferedImage.getGraphics();
        // 设置当前画笔颜色
        graphics.setColor(Color.GRAY);
        // 填充矩形，用当前画笔的颜色
        graphics.fillRect(0, 0, 200, 200);

        // 设置画笔的颜色为黑色，写字
        graphics.setColor(Color.BLACK);
        // 设置当前画笔的字体，以及大小，根据字体个数算
        graphics.setFont(new Font("宋体",Font.BOLD,200 / (len * 2 + 1)));

        int x = 20;
        int spacing = (200 - x) / len;
        // 画出名字
        for (int i = 0; i < len; i++) {
            graphics.drawString(customerName.charAt(i) + "",x,110);
            // 迁移，即产生间距
            x += spacing;
        }

        // 关闭画笔资源
        graphics.dispose();
        return bufferedImage;
    }


    /**
     * 生成图片存放的绝对磁盘路径
     * @param request
     * @return
     */
    public static  String getPicRealPath(HttpServletRequest request) {
        String realPath = request.getServletContext().getRealPath("/");
        String picPath;
        if (StringUtils.isEmpty(realPath)) {
            picPath = "/root/apache-tomcat-8.5.47/webapps/pic" + request.getContextPath() + "/"  ;
        } else {
            picPath = realPath.substring(0, realPath.indexOf(request.getContextPath().substring(1))) + "/pic" + request.getContextPath() + "/"  ;
        }
        return picPath;
    }
}
