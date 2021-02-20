package com.demo.crm.settings.service.impl;

import com.demo.crm.exception.LoginException;
import com.demo.crm.settings.dao.UserDao;
import com.demo.crm.settings.domain.User;
import com.demo.crm.settings.service.UserService;
import com.demo.crm.utils.DateTimeUtil;
import com.demo.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);


    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {

        Map<String,String> map = new HashMap<String,String>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        User user = userDao.login(map);

        if(user==null){

            throw new LoginException("账号密码错误");

        }

        //如果程序能成功地执行到该行，说明账号密码正确
        //需要继续向下验证其他三项信息

        //验证失效时间
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if(expireTime.compareTo(currentTime)<0){

            throw new LoginException("账号已失效");

        }

        //验证锁定状态
        String lockState = user.getLockState();
        if("0".equals(lockState)){

            throw new LoginException("账号已锁定");

        }

        //验证ip地址
        String allowips = user.getAllowIps();
        if(!allowips.contains(ip)){

            throw new LoginException("ip不允许访问");

        }


        return user;
    }

    @Override
    public List<User> getUserList() {

        List<User> uList = userDao.getUserList();

        return uList;
    }
}
