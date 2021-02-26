package com.demo.crm.workbench.dao;

import com.demo.crm.workbench.domain.Activity;

import java.util.List;

public interface ActivityDao {
    int save(Activity activity);

    List<Activity> getActivityListByCondition();
}
