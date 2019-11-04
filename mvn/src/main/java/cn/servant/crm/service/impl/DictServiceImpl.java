package cn.servant.crm.service.impl;

import cn.servant.crm.mapper.DictMapper;
import cn.servant.crm.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public List<Map<String, Object>> selectDictByTypeCode(String typeCode) {
        return dictMapper.selectDictByTypeCode(typeCode);
    }

    @Override
    public List<Map<String, Object>> selectDictToStatistic(Map<String,String> statisticType) {
        return dictMapper.selectDictToStatistic(statisticType);
    }

    @Override
    public String selectTypeNameByCode(String typeCode) {
        return dictMapper.selectTypeNameByCode(typeCode);
    }

}
