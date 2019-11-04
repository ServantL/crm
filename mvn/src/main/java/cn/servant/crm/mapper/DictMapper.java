package cn.servant.crm.mapper;

import cn.servant.crm.pojo.po.Dict;

import java.util.List;
import java.util.Map;

public interface DictMapper {


    public Dict selectDiceById(int id);

    public List<Map<String, Object>> selectDictByTypeCode(String typeCode);

    public List<Map<String, Object>> selectDictToStatistic(Map<String,String> statisticType);

    public String selectTypeNameByCode(String typeCode);
}
