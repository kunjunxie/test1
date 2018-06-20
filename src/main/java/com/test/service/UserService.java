package com.test.service;

import com.test.dao.LoginLogDao;
import com.test.dao.UserDao;
import com.test.domain.LoginLog;
import com.test.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
   private UserDao userDao;
   private LoginLogDao loginLogDao;
   public boolean hashMatchUser(String userName,String password){
       int matcnCount=userDao.getMatchCount(userName,password);
       return matcnCount>0;
   }
   public User findUserByUserName(String userName){
       return userDao.findUserByUserName(userName);
   }
   @Transactional
   public void loginSuccess(User user){
       user.setCredits( 5 +user.getCredits());
       LoginLog loginLog=new LoginLog();
      loginLog.setIp(user.getLastIp());
      loginLog.setUserId(user.getUserId());
      loginLog.setLoginDate(user.getLastVisit());
      userDao.updateLoginInfo(user);
      loginLogDao.insertLoginLog(loginLog);
   }
   @Autowired
    public void setUserDao(UserDao userDao){
       this.userDao=userDao;
   }
   @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao){
        this.loginLogDao=loginLogDao;
   }
}
