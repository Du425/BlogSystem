package com.du.blog.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.du.blog.entity.MUser;
import com.du.blog.service.IMUserService;
import com.du.blog.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    IMUserService imUserService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return super.supports(token);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //强转为JWT的token
        JwtToken jwtToken = (JwtToken) token;
        String userId = jwtUtil.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        MUser user = imUserService.getById(Long.valueOf(userId));
        if (user==null){
            throw new UnknownAccountException("账户不存在");
        }
        if (user.getStatus()==-1){
            throw new LockedAccountException("账户已被锁定");
        }
        //查出每个字段的意义
        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user,profile);

        //这里公开的信息是不比较私密的
        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }


}
