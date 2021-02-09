package com.gangbb.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author : Gangbb
 * @ClassName : MyRealm
 * @Description : 一个自定义realm 将认证和授权的的来源模拟为数据库数据
 * @Date : 2021/1/31 10:15
 */
public class MyRealm extends AuthorizingRealm  {
    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从token中获取用户名
        String principal = (String) token.getPrincipal();
        //模拟根据身份信息通过JDBC、Mybatis等从数据库查询相关数据
        if("Gangbb".equals(principal)){
            //参数1：数据库中的账号 参数2：数据库中的密码 参数3：当前realm的名字
            return new SimpleAuthenticationInfo(principal,"123",this.getName());
        }
        return null;
    }
}
