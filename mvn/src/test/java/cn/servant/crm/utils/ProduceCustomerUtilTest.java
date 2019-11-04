package cn.servant.crm.utils;

import cn.servant.crm.pojo.Customer;
import cn.servant.crm.pojo.po.Linkman;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

public class ProduceCustomerUtilTest {


    @Test
    public void getCustomer() throws Exception {
        Customer customer = ProduceCustomerUtil.getCusomer();
        System.out.println(customer);
    }


    /**
     * 客户基本信息
     */

    @Test
    public void produceLinkman() throws Exception {
        Linkman linkman = ProduceCustomerUtil.produceLinkman();
        System.out.println(linkman);
    }

    @Test
    public void produceName() {
        System.out.println(ProduceCustomerUtil.produceName());
    }

    @Test
    public void produceSex(){
        System.out.println(ProduceCustomerUtil.produceSex());
    }

    @Test
    public void producePhone(){
        System.out.println(ProduceCustomerUtil.producePhone());
    }

    @Test
    public void produceSource(){
        System.out.println(ProduceCustomerUtil.produceSource());
    }

    @Test
    public void produceIndustry(){
        System.out.println(ProduceCustomerUtil.produceIndustry());
    }

    @Test
    public void produceLevel(){
        System.out.println(ProduceCustomerUtil.produceLevel());
    }


    /**
     * 客户详细部分
     */

    @Test
    public void produceAddressAndRegion(){
        System.out.println(ProduceCustomerUtil.produceAddressAndRegion());
    }

    @Test
    public void produceCorporation(){
        System.out.println(ProduceCustomerUtil.produceCorporation());
    }


    @Test
    public void produceLicence(){
        System.out.println(ProduceCustomerUtil.produceLicence());
    }

    @Test
    public void produceCapital(){
        System.out.println(ProduceCustomerUtil.produceCapital());
    }

    @Test
    public void produceBank(){
        System.out.println(ProduceCustomerUtil.produceBank());
    }

    @Test
    public void producePic() throws IOException {
        System.out.println(ProduceCustomerUtil.producePic(ProduceCustomerUtil.produceName()));
    }
}
