package com.gangbb.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author : Gangbb
 * @ClassName : SaltMD5Realm
 * @Description :
 * @Date : 2021/1/31 11:16
 */
public class SaltMD5Realm extends AuthorizingRealm {
    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //根据用户名模拟到数据源获取权限以及权限信息
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println("primaryPrincipal = " + primaryPrincipal);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //模拟获取到了admin角色权限
        simpleAuthorizationInfo.addRole("admin");
        //模拟获取到用户对应资源权限，添加到simpleAuthorizationInfo
        simpleAuthorizationInfo.addStringPermission("user:update:*");
        simpleAuthorizationInfo.addStringPermission("product:*:*");



        return simpleAuthorizationInfo;
    }

    /**
     * 给传入密码做md5加密
     * @param password
     * @return
     */
    public String Md5Util(String password){
        Md5Hash md5Hash = new Md5Hash(password);
        return md5Hash.toHex();
    }

    /**
     * 给传入密码做md5+salt加密
     * @param password
     * @param salt
     * @return
     */
    public String Md5SaltUtil(String password, String salt){
        Md5Hash md5Hash = new Md5Hash(password, salt);
        return md5Hash.toHex();
    }

    /**
     * 给传入密码做md5+salt+hash散列次数 加密
     * @param password
     * @param salt
     * @param hash
     * @return
     */
    public String Md5SaltHashUtil(String password, String salt, Integer hash){
        Md5Hash md5Hash = new Md5Hash(password, salt, hash);
        return md5Hash.toHex();
    }

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        if ("Gangbb".equals(principal)) {
            //只是用md5加密
            //String password = this.Md5Util("999");
            //return new SimpleAuthenticationInfo(principal, password, this.getName());

            //使用md5+salt+hash
            String salt = "Gangbb-salt";
            Integer hash = 1024;
            String password1 = this.Md5SaltUtil("999", salt);
            String password2 = this.Md5SaltHashUtil("999", salt, hash);
            // 参数1：从数据源获取的用户名 参数2：从数据源获取的Md5+salt密码 参数3：密码处理的盐值 参数4：使用的realm的名字
            return new SimpleAuthenticationInfo(principal, password2, ByteSource.Util.bytes(salt), this.getName());

        }
        return null;
    }

}