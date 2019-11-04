package cn.servant.crm.controller;

import cn.servant.crm.mapper.CustomerBasicMapper;
import cn.servant.crm.pojo.Page;
import cn.servant.crm.pojo.po.CustomerBasic;
import cn.servant.crm.pojo.po.Visit;
import cn.servant.crm.pojo.vo.VisitVo;
import cn.servant.crm.service.CustomerService;
import cn.servant.crm.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisitService visitService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/list")
    public String list(Model model, VisitVo visitVo) throws Exception{
        if(visitVo.getPage() == null){
            visitVo.setPage(new Page());
        }
        if(visitVo.getVisit() == null){
            visitVo.setVisit(new Visit());
        }
        if(visitVo.getPage().getPageNow() == null){
            visitVo.getPage().setPageNow(1);
        }
        if(visitVo.getPage().getPageSize() == null){
            visitVo.getPage().setPageSize(5);
        }
        // 数据总行数
        int totalCount = visitService.getVisitTotalCount(visitVo);
        // 数据总页数
        int totalPages = visitService.getVisitTotalPages(visitVo);
        if(visitVo.getPage().getPageNow() > totalPages){
            visitVo.getPage().setPageNow(totalPages);
        }
        if (visitVo.getPage().getPageNow() <= 0 ) {
            visitVo.getPage().setPageNow(1);
        }
        visitVo.getPage().setTotalCount(totalCount);
        visitVo.getPage().setTotalPages(totalPages);
        // 数据
        List<Visit> visits = visitService.selectVisitByConditions(visitVo);
        model.addAttribute("visits", visits);
        model.addAttribute("page", visitVo.getPage());
        return "visit/list";
    }


    @RequestMapping("/add")
    public String add() throws Exception{
        return "visit/add";
    }


    @RequestMapping("/insertVisit")
    public String insertVisit(Visit visit) throws Exception{
        visitService.insertVisit(visit);
        return "success";
    }

    @RequestMapping("/getVisitCustomer")
    @ResponseBody
    public CustomerBasic getVisitCustomer(@RequestParam(value="visitPhone") String phone) throws Exception {
        CustomerBasic customerBasic = customerService.selectCustomerBasicByPhone(phone);
        return customerBasic;
    }
}
