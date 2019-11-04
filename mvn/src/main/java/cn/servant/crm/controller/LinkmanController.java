package cn.servant.crm.controller;

import cn.servant.crm.pojo.po.Linkman;
import cn.servant.crm.pojo.vo.LinkmanVo;
import cn.servant.crm.pojo.Page;
import cn.servant.crm.service.CustomerService;
import cn.servant.crm.service.LinkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/linkman")
public class LinkmanController {

    @Autowired
    private LinkmanService linkmanService;

    @Autowired
    private CustomerService customerService;


    /**
     * 联系人信息页面，根据参数进行条件查询
     * @param model
     * @param linkmanVo
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model, LinkmanVo linkmanVo) throws Exception {
        if (linkmanVo.getPage() == null) {
            linkmanVo.setPage(new Page());
        }
        if (linkmanVo.getLinkman() == null) {
            linkmanVo.setLinkman(new Linkman());
        }
        if (linkmanVo.getPage().getPageNow() == null) {
            linkmanVo.getPage().setPageNow(1);
        }
        if (linkmanVo.getPage().getPageSize() == null) {
            linkmanVo.getPage().setPageSize(5);
        }
        // 数据总行数
        int totalCount = linkmanService.getLinkmanTotalCount(linkmanVo);
        // 数据总页数
        int totalPages = linkmanService.getLinkmanTotalPages(linkmanVo);
        if (linkmanVo.getPage().getPageNow() > totalPages) {
            linkmanVo.getPage().setPageNow(totalPages);
        }
        if (linkmanVo.getPage().getPageNow() <= 0 ) {
            linkmanVo.getPage().setPageNow(1);
        }
        linkmanVo.getPage().setTotalCount(totalCount);
        linkmanVo.getPage().setTotalPages(totalPages);
        // 数据
        List<Linkman> linkmans = linkmanService.selectLinkmanByConditions(linkmanVo);
        model.addAttribute("linkmans", linkmans);
        model.addAttribute("page", linkmanVo.getPage());
        return "linkman/list";
    }


    /**
     * 联系人添加页面
     * @return
     */
    @RequestMapping("/add")
    public String add() {
        return "linkman/add";
    }


    /**
     * 添加联系人
     * @param linkmanVo
     * @return
     * @throws Exception
     */
    @RequestMapping("/insertLinkman")
    public String insertLinkman(LinkmanVo linkmanVo) throws Exception{
        linkmanService.insertLinkman(linkmanVo);
        return "success";
    }

    /**
     * 根据id查询用户
     * @param linkId
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectLinkmanById")
    @ResponseBody
    public String selectLinkmanById(Integer linkId) throws Exception{
        Linkman linkman = linkmanService.selectLinkmanById(linkId);
        if (linkman.getLinkCustomerList() == null || linkman.getLinkCustomerList().size() == 0) {
            // 该联系人门下有客户
            return "no";
        }
        return "have";
    }


    /**
     * 删除联系人，根据id
     * @param linkId
     */
    @RequestMapping("/deleteLinkman")
    @ResponseBody
    public String deleteLinkman(int linkId) throws Exception {
        Linkman linkman = linkmanService.selectLinkmanById(linkId);
        if (linkman.getLinkCustomerList() == null ||linkman.getLinkCustomerList().size() == 0) {
            linkmanService.deleteLinkmanById(linkId);
        }else{
            // 这时候有客户所以该联系人不能进行删除，所以给予提示，或者提供客户迁移的功能
            System.out.println("有客户");
            return "error";
        }
        return "success";
    }

    /**
     * 对客户进行迁移，linkId为原联系人的id，linkName为要迁移到所在的联系人的名称
     * 如果update能够返回行数则根据行数判断是否修改成功，
     * 因为要进行迁移所以该联系人门下不可能无客户，所以只有可能是不存在输入的联系人
     * 如果update不能够返回则先根据联系人名称查询该联系人，判断是否存在该联系人
     * 存在后再进行迁移，必定成功（用这种）
     *
     * 前台根据返回的字符串进行页面的跳转或重新提示
     * @param linkId
     * @param newLinkName
     * @return
     */
    @RequestMapping("/removeCustomers")
    @ResponseBody
    public String removeCustomers(Integer linkId,String newLinkName) throws Exception {
        Integer newLinkId = linkmanService.selectLinkmanIdByName(newLinkName);
        if (newLinkId == null) {
            return "error";
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("oldLinkId", linkId);
        map.put("newLinkId", newLinkId);
        customerService.removeCustomers(map);
        // 迁移完成后，该联系人门下就没有客户了，所以在此顺便进行删除联系人
        linkmanService.deleteLinkmanById(linkId);
        return "success";

    }


    /**
     * 联系人修改页面
     * @param linkId
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model,int linkId) throws Exception {
        model.addAttribute("linkman",linkmanService.selectLinkmanById(linkId));
        return "linkman/edit";
    }

    /**
     * 修改联系人信息
     * @param linkman
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateLinkman")
    @ResponseBody
    public String updateLinkman(Linkman linkman) throws Exception {
        linkmanService.updateLinkman(linkman);
        return "success";
    }


    @RequestMapping("selectLinkmanIdByPhone")
    @ResponseBody
    public String selectLinkmanIdByPhone(String phone) throws Exception {
        Integer linkId = linkmanService.selectLinkmanIdByPhone(phone);
        if (linkId == null) {
            return "noExist";
        }
        return "exist";
    }

}
