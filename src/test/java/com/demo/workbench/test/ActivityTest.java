package com.demo.workbench.test;

import com.demo.crm.utils.ServiceFactory;
import com.demo.crm.utils.UUIDUtil;
import com.demo.crm.workbench.domain.Activity;
import com.demo.crm.workbench.service.ActivityService;
import com.demo.crm.workbench.service.impl.ActivityServiceImpl;
import org.junit.Test;

public class ActivityTest {

    @Test
    public void testSave(){

//        System.out.println("111");

        Activity a = new Activity();
        a.setId(UUIDUtil.getUUID());
        a.setName("中文");

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.save(a);

        System.out.println(flag);

    }

}
