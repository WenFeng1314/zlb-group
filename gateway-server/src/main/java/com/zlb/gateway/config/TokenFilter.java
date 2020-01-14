package com.zlb.gateway.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.bouncycastle.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class TokenFilter extends ZuulFilter {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String TOKEN_KEY="token_key";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }
    //1.分析：是否拦截
    //a.登录不需要拦截
    //b.其他的都拦截
    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //再获取请求的uri
        String requestURI = request.getRequestURI();
        if (StringUtils.hasText(requestURI)&&requestURI.endsWith("/login")){
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        // Authorization 必须手动在zuul 里面放行它
        String authorization = request.getHeader("Authorization");
        if (Objects.nonNull(authorization)||!redisTemplate.hasKey(TOKEN_KEY)){
            currentContext.setResponseStatusCode(401);
            currentContext.setResponseBody("{\"code\":401,\"msg\":\"没有授权\"}");
            currentContext.setSendZuulResponse(false);
        }
        currentContext.setSendZuulResponse(true);
        return null;
    }
}
