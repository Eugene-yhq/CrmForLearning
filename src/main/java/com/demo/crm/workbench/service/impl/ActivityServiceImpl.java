package com.demo.crm.workbench.service.impl;

import com.demo.crm.utils.ServiceFactory;
import com.demo.crm.utils.SqlSessionUtil;
import com.demo.crm.vo.PaginationVO;
import com.demo.crm.workbench.dao.ActivityDao;
import com.demo.crm.workbench.domain.Activity;
import com.demo.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

    @Override
    public boolean save(Activity activity) {

        boolean flag = true;

        int count = activityDao.save(activity);

        if(count!=1){

            flag = false;

        }

        return flag;
    }

    public PaginationVO<Activity> pageList(Map<String, Object> map) {

        //取得total
        int total = activityDao.getTotalByCondition(map);

        //取得dataList
        List<Activity> dataList = activityDao.getActivityListByCondition(map);

        //将total和dataList封装到vo中
        PaginationVO<Activity> vo = new PaginationVO<Activity>();
        vo.setTotal(total);
        vo.setDataList(dataList);

        //将vo返回

        return vo;
    }

    @Override
    public boolean delete(String[] ids) {


        return false;
    }
}
