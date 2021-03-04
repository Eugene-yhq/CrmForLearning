package com.demo.crm.workbench.service.impl;

import com.demo.crm.utils.SqlSessionUtil;
import com.demo.crm.workbench.dao.ClueActivityRelationDao;
import com.demo.crm.workbench.dao.ClueDao;
import com.demo.crm.workbench.domain.Clue;
import com.demo.crm.workbench.service.ClueService;

public class ClueServiceImpl implements ClueService {

    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
    private ClueActivityRelationDao clueActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);

    @Override
    public boolean save(Clue c) {

        boolean flag = true;

        int count = clueDao.save(c);

        if(count!=1){

            flag = false;
        }

        return flag;
    }

    @Override
    public Clue detail(String id) {

        Clue c = clueDao.detail(id);

        return c;
    }

    @Override
    public boolean unbund(String id) {

        boolean flag = true;

        int count = clueActivityRelationDao.unbund(id);

        if(count!=1){

            flag = false;

        }

        return flag;
    }
}
