package com.demo.crm.settings.service.impl;

import com.demo.crm.settings.dao.DicTypeDao;
import com.demo.crm.settings.dao.DicValueDao;
import com.demo.crm.settings.domain.DicType;
import com.demo.crm.settings.domain.DicValue;
import com.demo.crm.settings.service.DicService;
import com.demo.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicServiceImpl implements DicService {

    private DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    private DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);

    @Override
    public Map<String, List<DicValue>> getAll() {

        Map<String,List<DicValue>> map = new HashMap<String, List<DicValue>>();

        //将字典类型列表取出
        List<DicType> dtList = dicTypeDao.getTypeList();

        //将字典类型列表遍历
        for(DicType dt : dtList){

            //取得每一种类型的字典类型编码
            String code = dt.getCode();

            //根据每一个字典类型来取得字典值的列表
            List<DicValue> dvList = dicValueDao.getListByCode(code);

            map.put(code, dvList);

        }


        return map;
    }
}
