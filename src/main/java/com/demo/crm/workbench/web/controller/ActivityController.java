package com.demo.crm.workbench.web.controller;

import com.demo.crm.settings.domain.User;
import com.demo.crm.settings.service.UserService;
import com.demo.crm.settings.service.impl.UserServiceImpl;
import com.demo.crm.utils.*;
import com.demo.crm.vo.PaginationVO;
import com.demo.crm.workbench.dao.ActivityDao;
import com.demo.crm.workbench.domain.Activity;
import com.demo.crm.workbench.service.ActivityService;
import com.demo.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入到市场活动控制器");

        String path = request.getServletPath();

        if("/workbench/activity/getUserList.do".equals(path)){
            
            getUserList(request,response);

        }else if("/workbench/activity/save.do".equals(path)){

            save(request,response);

        }else if("/workbench/activity/pageList.do".equals(path)){

            pageList(request,response);

        }

    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("进入到查询市场活动信息列表的操作（结合条件查询+分页查询）");

        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //每页展现的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        //计算出掠过的记录数
        int skipCount = (pageNo - 1) * pageSize;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        /*

            前端要：市场活动信息列表和查询的总条数

            业务层拿到以上两项信息之后，如果做返回
            有两种形式
            map
            map.put("dataList":dataList)
            map.put("total":total)
            PrintJSON map --> json
            {"total":100,"dataList":[{市场活动1}{2}{3}]}

            vo
            Pagination<T>
                private int total;
                private List<T> dataList;

            paginationVO<Activity> vo = new PaginationVO<>;
            vo.setTotal(total);
            vo.setDataList(dataList);
            PrintJSON vo -->json
            {"total":100,"dataList":[{市场活动1}{2}{3}]}

         */

        PaginationVO<Activity> vo = as.pageList(map);

        PrintJson.printJsonObj(response, vo);


    }

    private void save(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行市场活动的添加操作");

        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");

        //创建时间为当前系统时间
        String createTime = DateTimeUtil.getSysTime();
        //创建人为当前用户
        String createBy = ((User) request.getSession().getAttribute("user")).getName();

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        Activity activity = new Activity();
        activity.setCost(cost);
        activity.setStartDate(startDate);
        activity.setOwner(owner);
        activity.setName(name);
        activity.setId(id);
        activity.setEndDate(endDate);
        activity.setDescription(description);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);

        boolean flag = as.save(activity);

        PrintJson.printJsonFlag(response, flag);

    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("取得用户信息列表");

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        List<User> uList = us.getUserList();

        PrintJson.printJsonObj(response, uList);

    }


}
