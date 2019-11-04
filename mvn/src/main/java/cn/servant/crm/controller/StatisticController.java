package cn.servant.crm.controller;

import cn.servant.crm.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private DictService dictService;

    @RequestMapping("/dictStatistic")
    public String sourceStatistic(Model model,String statisticTypeName,String statisticTypeCode) {
        /**
         * 后台进行客户的分组查询
         * 因为客户的来源和行业都是在数据字典表中的，
         * 所以要进行表联合查询？？？，查询条件限制对字典的类型，
         * 然后再根据该类型的item进行分组？？？
         * 定义一个pojo类，里面封装对应的客户来源类型，及其人数？？？
         * 因为还要对客户行业进行分类，所以里面的也可能是客户行业的类型，
         * 或者不需要定义pojo，用map封装key-value即可，对应前台data的name-value（暂时用这个）
         * 前台传输的时候根据所点的统计类型不同给它默认一个隐式input传输一个数据类型代码
         * 所以只需要一个Controller即可？？？
         */
        Map<String, String> statisticType = new HashMap<String, String>();
        statisticType.put("statisticTypeName", statisticTypeName);
        statisticType.put("statisticTypeCode", statisticTypeCode);
        List<Map<String,Object>> statisticList = dictService.selectDictToStatistic(statisticType);
        String typeName = dictService.selectTypeNameByCode(statisticTypeCode);
        model.addAttribute("statisticList", statisticList);
        model.addAttribute("typeName", typeName);
        return "statistic/dictStatistic";
    }


    @RequestMapping("/industryStatistic")
    public String industryStatistic() {
        return "statistic/industryStatistic";
    }


}
