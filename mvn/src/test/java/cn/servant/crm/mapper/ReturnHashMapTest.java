package cn.servant.crm.mapper;

import cn.servant.crm.pojo.po.CustomerBasic;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class ReturnHashMapTest {

    @Autowired
    private CustomerBasicMapper customerBasicMapper;

    private ApplicationContext applicationContext;

    //在setUp这个方法得到spring容器
    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
    }

    @Test
    public void selectTest() throws Exception {
        CustomerBasicMapper customerBasicMapper = (CustomerBasicMapper) applicationContext.getBean("customerBasicMapper");
        CustomerBasic customerBasic = customerBasicMapper.selectTest();
//        Map map = customerBasic.getTestSource();
//        Map<Object,Object> baseMap = DictResultUtil.getBaseMap(map);
//        System.out.println(map);
    }

    @Test
    public void selectTest1() throws Exception {
        CustomerBasicMapper customerBasicMapper = (CustomerBasicMapper) applicationContext.getBean("customerBasicMapper");
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("1", "2");
        CustomerBasic customerBasic = customerBasicMapper.selectTest1(map);
//        Map baseMap = customerBasic.getTestSource();
        System.out.println(map);
    }


}
