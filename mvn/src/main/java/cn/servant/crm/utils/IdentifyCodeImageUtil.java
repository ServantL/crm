package cn.servant.crm.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

public class IdentifyCodeImageUtil {

    /**
     * 获取验证码和验证码图片的一个map集合
     * 待改进，其宽高等参数可提出来静态变量，使用是随机更改以画出不同的验证码图片
     * 而且间距需要进行计算，所以写一个返回间距的方法，根据宽高字体大小等
     * 字体也提出，有默认值，当用户可更改，
     * 最好多重载几个方法，因为或许要验证码中的每个字符的字体都不一样等，即传入字体数组
     * @return
     */
    public static HashMap<String,Object> getIdentifyCode() {
        // 宽
        int width = 100;
        // 高
        int height = 30;
        // 干扰线
        int disturbLine = 10;
        // 验证码个数
        int number = 4;
        // 储存拼接验证码的字符，即验证码的字符从该字符串从提取
        String bank = "qwertyuiopasdfghjklzxcvbnm";
        // 控制字符的迁移，即产生验证码的字符的间距
        int x = 5;
        // 新建缓存图片
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        // 获取画笔，图片的形成都是基于画笔去化的
        Graphics graphics = bufferedImage.getGraphics();
        // 设置当前画笔颜色
        graphics.setColor(Color.YELLOW);
        // 填充矩形，用当前画笔的颜色
        graphics.fillRect(0, 0, width, height);

        // 设置当前画笔颜色
        graphics.setColor(Color.BLUE);
        // 画矩形，可理解为边框
        graphics.drawRect(0, 0, width - 1, height - 1);
        // 设置当前画笔颜色
        graphics.setColor(Color.GRAY);
        // 设置当前画笔字体
        graphics.setFont(new Font("宋体", Font.BOLD, 20));
        // 随机数产生器
        Random random = new Random();

        // 随机画n条干扰线
        for (int i = 0; i < disturbLine; i++) {
            // 两点确定一条直线，所以需要随机产生两个点的坐标
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            // 画线
            graphics.drawLine(x1, y1, x2, y2);
        }
        // 用来拼接产生的验证码
        StringBuilder sb = new StringBuilder();

        graphics.setColor(Color.RED);
        // 画n个字符的验证码
        for (int i = 0; i < number; i++) {
            // 获取存储字符串的长度，随机获取一个索引
            int index = random.nextInt(bank.length());
            // 根据索引获取该字符
            String section = String.valueOf(bank.charAt(index));
            // 获取一个随机true或false用来随机该字符的大小写
            if (random.nextBoolean()) {
                section = section.toUpperCase();
            }
            // 拼接字符串
            sb.append(section);
            // 画字符
            graphics.drawString(section, x, 18);
            // 迁移，即间距
            x += 25;
        }

        // 因为要返回验证码以及验证码图片所以用map，
        // 或者可以更改为将request传进来后直接将验证码存放到session中，而返回验证码图片即可
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("identifyCode", sb.toString());
        map.put("identifyCodeImage", bufferedImage);

        // 关闭画笔资源
        graphics.dispose();
        return map;
    }

}
