package com.gangbb;

import com.gangbb.realm.SaltMD5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

/**
 * @author : Gangbb
 * @ClassName : TestSaltMD5Realm
 * @Description :
 * @Date : 2021/1/31 11:21
 */
public class TestSaltMD5Realm {
    public static void main(String[] args) {
        //创建安全管理器securityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //IniRealm realm = new IniRealm("classpath:shiro.ini");
        //设置为自定义realm获取认证数据
        SaltMD5Realm saltMD5Realm = new SaltMD5Realm();
        //定义密码匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //给密码匹配器设置md5校验
        credentialsMatcher.setHashAlgorithmName("MD5");
        //给密码匹配器设置散列次数
        credentialsMatcher.setHashIterations(1024);
        //把 md5+salt+hash 密码匹配器装入saltMD5Realm
        saltMD5Realm.setCredentialsMatcher(credentialsMatcher);
        //把saltMD5Realm交给defaultSecurityManager完成校验
        defaultSecurityManager.setRealm(saltMD5Realm);
        //将安装工具类中设置安全管理器
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //创建token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("Gangbb", "999");
        try {
            //用户登录
            subject.login(token);
            System.out.println("登录成功~~");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误!!");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误!!!");
        }

        //如果认证通过，就进行授权
        if(subject.isAuthenticated()){
            //基于角色权限管理
            //判断单个角色
            boolean single = subject.hasRole("admin");
            System.out.println(single);

            //判断多个角色
            boolean many = subject.hasAllRoles(Arrays.asList("role1", "role2"));
            System.out.println(many);

            //判断多个角色中是否有其中一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("role1", "role2"));
            for(Boolean b:booleans){
                System.out.println(b);
            }

            //或者使用check方法，若无权限抛出UnauthorizedException
            //subject.checkRole("admin");
            //subject.checkRoles(Arrays.asList("role1", "role2"));


            // 基于资源授权 单个资源
            boolean permitted = subject.isPermitted("product:create:001");
            System.out.println(permitted);
            //多个资源
            boolean permitted2 = subject.isPermittedAll("user:create:1",	"user:delete");
            System.out.println(permitted2);

            //基于资源授权 对应check方法
//            subject.checkPermission("sys:user:delete");
//            subject.checkPermissions("user:create:1","user:delete");

        }

    }
}
