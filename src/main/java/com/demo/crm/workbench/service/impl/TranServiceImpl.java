package com.demo.crm.workbench.service.impl;

import com.demo.crm.utils.SqlSessionUtil;
import com.demo.crm.workbench.dao.TranDao;
import com.demo.crm.workbench.domain.TranHistory;
import com.demo.crm.workbench.service.TranService;

public class TranServiceImpl implements TranService {

    private TranDao tranDao = SqlSessionUtil.getSqlSession().getMapper(TranDao.class);
    private TranHistory tranHistory = SqlSessionUtil.getSqlSession().getMapper(TranHistory.class);

}
