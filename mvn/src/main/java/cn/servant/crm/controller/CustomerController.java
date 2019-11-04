package cn.servant.crm.controller;

import cn.servant.crm.pojo.Customer;
import cn.servant.crm.pojo.po.CustomerBasic;
import cn.servant.crm.pojo.po.CustomerDetail;
import cn.servant.crm.pojo.po.Linkman;
import cn.servant.crm.pojo.vo.CustomerVo;
import cn.servant.crm.pojo.Page;
import cn.servant.crm.service.CustomerService;
import cn.servant.crm.service.DictService;
import cn.servant.crm.service.LinkmanService;
import cn.servant.crm.utils.DefaultCustomerPicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LinkmanService linkmanService;

    @Autowired
    private DictService dictService;

    /**
     * 客户级别下拉框
     * @return
     */
    @ModelAttribute("customerLevel")
    public List<Map<String,Object>> getCustomerLevel(){
        return dictService.selectDictByTypeCode("003");
    }

    /**
     * 客户来源下拉框
     * @return
     */
    @ModelAttribute("customerSource")
    public List<Map<String,Object>> getCustomerSource() {
        return dictService.selectDictByTypeCode("001");
    }

    /**
     * 客户行业下拉框
     * @return
     */
    @ModelAttribute("customerIndustry")
    public List<Map<String,Object>> getCustomerIndustry(){
        return dictService.selectDictByTypeCode("002");
    }
    /**
     * 在添加或编辑页面选择联系人的姓名
     * @return
     */
    @ModelAttribute("linkmanNameId")
    public Map<String,Integer> getLinkmanNameId() throws Exception {
        Map<String, Integer> linkmanNameId = new HashMap<String, Integer>();
        List<Linkman> linkmanList = linkmanService.selectLinkmanNameId();
        for (Linkman linkman : linkmanList) {
            linkmanNameId.put(linkman.getLinkName(), linkman.getLinkId());
        }
        return linkmanNameId;
    }



    /**
     * 插入客户信息
     * @param
     * @return
     */
    @RequestMapping("/insertCustomer")
    public String insertCustomer(Model model,@Validated Customer customer, BindingResult bindingResult, MultipartFile pictureFile) throws Exception {
        // 判断是否有校验错误信息
        if (bindingResult.hasErrors()) {
//            Map map = bindingResult.getModel();
            List<ObjectError> messageErrors = bindingResult.getAllErrors();
            // 出现错误将错误信息回显到页面中
            model.addAttribute("messageErrors", messageErrors);
            return "customer/add";
        }

        // 对图片进行处理
        if (pictureFile != null) {
            // 获取图片的原名称
            String originalFilename = pictureFile.getOriginalFilename();
            // 存放图片的绝对磁盘路径
            String picPath = "D:\\IntelliJ IDEA 2018.3\\pic\\crm\\";
            if (originalFilename != null && originalFilename.length() > 0) {
                // 新图片的名称
                String newFilename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
                // 新图片，new File（图片路径）
                File newFile = new File(picPath + newFilename);
                // 将原图片拷贝到新图片中
                pictureFile.transferTo(newFile);
                // customer中custPic为图片的名称
                customer.getCustomerDetail().setCustPic(newFilename);
            }else{
                // 新图片名称
                String newFilename = UUID.randomUUID() + ".jpg";
                // 新图片
                File newFile = new File(picPath + newFilename);
                // 获取用户默认图片
                BufferedImage bufferedImage = DefaultCustomerPicUtil.getDefaultCustomerPic(customer.getCustomerBasic().getCustName());
                // 将客户图片写到新图片中
                ImageIO.write(bufferedImage, "jpg",newFile);
                // customer中custPic为图片的名称
                customer.getCustomerDetail().setCustPic(newFilename);
            }
        }
        customerService.insertCustomer(customer);
        return "success";
    }


    /**
     * 客户信息页面，根据参数进行条件查询
     * @param model
     * @param customerVo
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model, CustomerVo customerVo) throws Exception {
        // 第一次访问时没有customerVo参数，所有需要new出来避免出现空指针异常（后可改动为拦截器）
        if (customerVo.getPage() == null) {
            customerVo.setPage(new Page());
        }
        if (customerVo.getCustomer() == null) {
            customerVo.setCustomer(new Customer(new CustomerBasic(),new CustomerDetail()));
        }else{
            model.addAttribute("selectedSource", customerVo.getCustomer().getCustomerBasic().getCustSource().get("key"));
            model.addAttribute("selectedIndustry", customerVo.getCustomer().getCustomerBasic().getCustIndustry().get("key"));
            model.addAttribute("selectedLevel", customerVo.getCustomer().getCustomerBasic().getCustLevel().get("key"));
            model.addAttribute("selectedLinkName", customerVo.getCustomer().getCustomerBasic().getCustLinkman().getLinkName());

        }
        if (customerVo.getPage().getPageNow() == null) {
            customerVo.getPage().setPageNow(1);
        }
        if (customerVo.getPage().getPageSize() == null) {
            customerVo.getPage().setPageSize(5);
        }
        // 数据总行数
        int totalCount = customerService.getCustomerTotalCount(customerVo);
        // 数据总页数
        int totalPages = customerService.getCustomerTotalPages(customerVo);
        if (customerVo.getPage().getPageNow() > totalPages) {
            customerVo.getPage().setPageNow(totalPages);
        }
        if (customerVo.getPage().getPageNow() <= 0 ) {
            customerVo.getPage().setPageNow(1);
        }
        customerVo.getPage().setTotalCount(totalCount);
        customerVo.getPage().setTotalPages(totalPages);
        // 数据
        List<CustomerBasic> customerBasics = customerService.selectCustomerByConditions(customerVo);
        model.addAttribute("customerBasics", customerBasics);
        model.addAttribute("page", customerVo.getPage());
        return "customer/list";
    }



    /**
     * 添加客户页面
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model, String visitPhone) {
        model.addAttribute("visitPhone", visitPhone);
        return "customer/add";
    }



    /**
     * 编辑客户信息页面
     * @param model
     * @param custId
     * @return
     */
    @RequestMapping("/edit")
    public String edit(int custId,Model model) throws Exception {
        Customer customer = customerService.selectCustomerById(custId);
        model.addAttribute("customer",customer);
        return "customer/edit";
    }



    /**
     * 删除客户
     * @param custId
     * @return
     */
    @RequestMapping("/deleteCustomer")
    public String deleteCustomer(int custId) throws Exception {
        customerService.deleteCustomerById(custId);
        return "success";
    }

    /**
     * 修改客户信息
     * @param customer
     * @return
     */
    @RequestMapping("/updateCustomer")
    public String updateCustomer(Model model,@Validated  Customer customer,BindingResult bindingResult, MultipartFile pictureFile) throws Exception {
        // 判断是否有校验错误信息
        if (bindingResult.hasErrors()) {
//            Map map = bindingResult.getModel();
            List<ObjectError> messageErrors = bindingResult.getAllErrors();
            // 出现错误将错误信息回显到页面中
            model.addAttribute("messageErrors", messageErrors);
            return "customer/add";
        }

        // 对图片进行处理
        if (pictureFile != null) {
            String originalFilename = pictureFile.getOriginalFilename();
            if (originalFilename != null && originalFilename.length() > 0) {
                // 存储图片的物理磁盘路径
                String picPath = "D:\\IntelliJ IDEA 2018.3\\pic\\crm\\";
                // 如果原先有图片，则将原图片删除，因为custpic记录的是图片的名称，所以要还原成路径
                String formerFilename = customer.getCustomerDetail().getCustPic();
                if (formerFilename != null && formerFilename.length() > 0) {
                    File formerFile = new File(picPath + formerFilename);
                    formerFile.delete();
                }
                // 图片的新名称，随机UUID + 原名称的后缀
                String newFilename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf('.'));
                // 新图片，路径 + 名称
                File newFile = new File(picPath + newFilename);
                // 将pictureFile的数据写到newFile中，即该操作实则为保存图片的操作
                pictureFile.transferTo(newFile);
                // 因为图片的物理磁盘路径有其对应的虚拟路径，所以Customer中的pic属性为图片的名称即可
                customer.getCustomerDetail().setCustPic(newFilename);
            }
        }
        customerService.updateCustomer(customer);
        return "success";
    }

    /**
     * 修改图片时如果有换新的，则将老的删除，
     * 在页面的时候，当选择图片时，使用js将显示图片的img更改
     */
    @RequestMapping("/refreshPicture")
    @ResponseBody
    public String refreshPicture(MultipartFile pictureFile,String imgPath) throws IOException {
        // 同一将临时图片命名为temp + 原名称后缀
        String newFilename = "";
        // 对图片进行处理
        if (pictureFile != null) {
            String originalFilename = pictureFile.getOriginalFilename();
            if (originalFilename != null && originalFilename.length() != 0) {
                // 存储图片的磁盘路径
                String picPath = "D:\\IntelliJ IDEA 2018.3\\pic\\crm\\";
                // 图片的新名称，temp + 原名称的后缀
//            if (imgPath != null && imgPath.length() > 0 && "temp".equals(imgPath.substring(imgPath.lastIndexOf('/') + 1, imgPath.lastIndexOf('.')))) {
//                    newFilename = "casual" + originalFilename.substring(originalFilename.lastIndexOf('.'));
//                }else{
                    newFilename = "temp" + originalFilename.substring(originalFilename.lastIndexOf('.'));
//                }
                // 新图片，new File（绝对磁盘路径）
                File newFile = new File(picPath + newFilename);
                // 先将原临时图片删除
                newFile.delete();
                // 将pictureFile的数据写到newFile中，即该操作实则为保存图片的操作
                pictureFile.transferTo(newFile);
            }
        }
        else {
            return imgPath.substring(imgPath.lastIndexOf('/') + 1);
        }
        String vision = String.valueOf(Math.random());
        return newFilename + "?v=" + vision.substring(2,vision.length()%10);
    }


    @RequestMapping("selectCustomerIdByPhone")
    @ResponseBody
    public String selectCustomerIdByPhone(String phone) throws Exception {
        Integer custId = customerService.selectCustomerIdByPhone(phone);
        if (custId == null) {
            return "noExist";
        }
        return "exist";
    }
}
