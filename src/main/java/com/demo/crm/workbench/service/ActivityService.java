package com.demo.crm.workbench.service;

import com.demo.crm.vo.PaginationVO;
import com.demo.crm.workbench.domain.Activity;

import java.util.Map;

public interface ActivityService {


    boolean save(Activity activity);

    PaginationVO<Activity> pageList(Map<String, Object> map);

    boolean delete(String[] ids);

    Map<String, Object> getUserListAndActivity(String id);
}
