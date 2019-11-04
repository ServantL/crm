package cn.servant.crm.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 舍弃不用，因为key-value在mybatis中只能根据key获取value，而不能获取到其key的值
 */
public class DictResultUtil {


    /**
     * 对数据字典查询出来的map集合进行合并，
     * 因为默认查询出来的为List<Map>集合，且key为字段名，value为字段值，
     * 则一个map中有两个数据，（想要的结果为id-name，只需要一个map），将两个数据合并为一个数据
     * 又因为要进行转化，所有mapper将查询出来的map的id的key命名为key，map的name的key命名为value，
     * 这样可以方便转换，转化要需要判断传进来的List<Map>还是当个<Map>，进行不同的转换
     * @param map
     * @return
     */
    public static Map<String,Object> getBaseMap(Map<String,Object> map) {
        Object key =  map.get("key");
        Object value = map.get("value");
        Map<String, Object> baseMap = new HashMap<String, Object>();
        baseMap.put(String.valueOf(key), value);
        return baseMap;
    }
}
