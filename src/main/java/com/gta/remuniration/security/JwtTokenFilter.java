package com.gta.remuniration.security;

import com.gta.remuniration.entity.RestError;
import com.gta.remuniration.exception.InvalidJwtAuthenticationException;
import com.gta.remuniration.exception.UserDeactivatedException;
import com.gta.remuniration.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static com.gta.remuniration.exception.FunctionalErrorCode.INVALID_JWT_TOKEN;
import static com.gta.remuniration.exception.FunctionalErrorCode.USER_DEACTIVATED;
import static com.gta.remuniration.utils.MapperUtils.mapToJson;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

public class JwtTokenFilter extends GenericFilterBean {
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
    

            try {
                System.out.println("hellofiltre");

                String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
                System.out.println(token);
                if (token != null && jwtTokenProvider.validateToken(token)) {
                    Authentication auth = jwtTokenProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (InvalidJwtAuthenticationException e) {
                HttpServletResponse httpServletResponse = (HttpServletResponse) res;
                httpServletResponse.setStatus(SC_UNAUTHORIZED);
                httpServletResponse.setHeader("content-type", MediaType.APPLICATION_JSON_VALUE);
                httpServletResponse.getWriter().print(mapToJson(new RestError(INVALID_JWT_TOKEN.getCode(), e.getMessage())));
            } catch (UserDeactivatedException e) {
                HttpServletResponse httpServletResponse = (HttpServletResponse) res;

                httpServletResponse.setStatus(SC_UNAUTHORIZED);
                httpServletResponse.setHeader("content-type", MediaType.APPLICATION_JSON_VALUE);
                httpServletResponse.getWriter().print(mapToJson(new RestError(INVALID_JWT_TOKEN.getCode(), e.getMessage())));
            }
            filterChain.doFilter(req, res);
        }

}
