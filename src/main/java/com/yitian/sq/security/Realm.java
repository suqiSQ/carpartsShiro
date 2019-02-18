package com.yitian.sq.security;

import com.yitian.sq.model.Menu;
import com.yitian.sq.model.Role;
import com.yitian.sq.model.User;
import com.yitian.sq.service.UserService;
import com.yitian.sq.util.SHA1Util;
import com.yitian.sq.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Realm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /**
     * 给用户授权（角色，菜单）
     * shirofilter 权限判断1：拦截规律（什么样的用户可以访问什么url）
     *                     2:当前 subject 拥有什么角色权限
     * 手动给用户的subject授权/授予角色 只授权一次,什么时候触发授权方法呢？
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        // 根据用户查到权限和角色
        List<Role> roleList = userService.findRoleByUser(user);
        List<Menu> menuList = userService.findMenuByUser(user);

        SimpleAuthorizationInfo info= new SimpleAuthorizationInfo();
        Set<String> rolesSet= new HashSet<>();
        Set<String> persSet= new HashSet<>();
        //把用户角色和权限存到set,再存到shiro安全管理中
        for(Role role:roleList){
            rolesSet.add(role.getRoleEnglishName());
        }
        for(Menu menu:menuList){
            persSet.add(menu.getUrl());
        }
        info.setRoles(rolesSet);
        info.setStringPermissions(persSet);
        return info;
    }

    /**
     * 登录认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        LoginToken loginToken= (LoginToken) authenticationToken;
        //取shiro 提供的session
        Session session= SecurityUtils.getSubject().getSession();
        //取验证码
        String validateCode= (String) session.getAttribute("validate");
        if(loginToken.getValidate()==null||!loginToken.getValidate().toUpperCase().equals(validateCode.toUpperCase())){
            throw new AccountException("验证码错误");
        }
        UserVo userVo = new UserVo();
        userVo.setLoginName(loginToken.getUsername());
                                                       //取出的密码是个数组
        userVo.setPassword(SHA1Util.encode(new String(loginToken.getPassword())));
        User user = userService.findUserByLoginNameAndPassword(userVo);
        if(user==null){
            throw new AccountException("用户或密码错误");
        }
        session.setAttribute("user",user);
        //返回认证信息，也是将用户的信息存起来（shirofilter 要用）
        return new SimpleAuthenticationInfo(user,loginToken.getPassword(),getName());
    }
}
