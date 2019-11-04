package cn.servant.crm.controller.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将日期形式的字符串转化为日期
 */
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        SimpleDateFormat simpleDateFormat;
        if (s.length() > 10) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }else{
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        try{
            return  simpleDateFormat.parse(s);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }
}
