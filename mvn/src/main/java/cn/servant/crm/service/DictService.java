package cn.servant.crm.service;

import java.util.List;
import java.util.Map;

public interface DictService {

    public List<Map<String, Object>> selectDictByTypeCode(String typeCode);

    public List<Map<String, Object>> selectDictToStatistic(Map<String,String> statisticType);

    public String selectTypeNameByCode(String typeCode);
}
