package com.demo.crm.settings.service.impl;

import com.demo.crm.settings.dao.DicTypeDao;
import com.demo.crm.settings.dao.DicValueDao;
import com.demo.crm.settings.service.DicService;
import com.demo.crm.utils.SqlSessionUtil;

public class DicServiceImpl implements DicService {

    private DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    private DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);

}
