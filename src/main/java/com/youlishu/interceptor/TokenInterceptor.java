package com.youlishu.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.youlishu.bean.LoginToken;
import com.youlishu.bean.ResponseBean;
import com.youlishu.dao.LoginMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yujin
 * @date 2021/6/24 10:27
 **/
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        /** 地址过滤 */
        StringBuffer uri = request.getRequestURL();
        if (String.valueOf(uri).contains("/login")) {
            return true;
        }
        else if (String.valueOf(uri).contains("/swagger")){
            return true;
        }
        try {
            /** Token 验证 */
            String token = request.getHeader("token");
            String username = request.getHeader("username");
            final ResponseBean error = new ResponseBean(401, "token lose efficacy", null);

            if (StringUtils.isEmpty(token)) {

                response.getWriter().write(JSONObject.toJSONString(error));
                return false;
            }
            LoginToken validToken = loginMapper.getToken(username);
            if(StringUtils.isBlank(validToken.getToken()) || !validToken.getToken().equals(token) ){
                response.getWriter().write(JSONObject.toJSONString(error));
                return  false;
            }

        } catch (Exception e) {
            ResponseBean error = new ResponseBean(401, "token lose efficacy", null);
            response.getWriter().write(JSONObject.toJSONString(error));
            return false;
        }
        return true;
    }
}
