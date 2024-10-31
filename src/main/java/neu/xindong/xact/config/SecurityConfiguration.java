package neu.xindong.xact.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.dto.response.SysUserAuthorize;
import neu.xindong.xact.entity.SysUser;
import neu.xindong.xact.filter.JWTAuthorizer;
import neu.xindong.xact.service.SysUserService;
import neu.xindong.xact.util.JWTUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
public class SecurityConfiguration {
    @Resource
    JWTUtil jwtUtil;

    @Resource
    JWTAuthorizer authorizer;

    @Resource
    SysUserService service;

    @Resource
    ObjectMapper objMapper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(conf ->
                        conf
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers(
                                        "/dev/**",
                                        "/API**",
                                        "/swagger-ui/**",
                                        "/error",
                                        "/v3/api-docs/**",
                                        "/swagger-ui.html"
                                ).permitAll()
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                                .permitAll()

                                .anyRequest().permitAll()
                                //.anyRequest().authenticated()
                )
                .formLogin(conf -> conf
                        .loginProcessingUrl("/api/auth/login")
                        .successHandler(this::onAuthenticationSuccess)
                        .failureHandler(this::onAuthenticationFailure)
                )
                .logout(conf -> conf.logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(this::onLogoutSuccess)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(conf -> conf
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authorizer, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(conf -> conf
                        .authenticationEntryPoint(this::onUnauthorized)
                )
                .build();

    }

    // Authentication-related methods
    private void onUnauthorized(HttpServletRequest req,
                                HttpServletResponse resp,
                                AuthenticationException e)
            throws IOException, ServletException {

        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(
                objMapper.writeValueAsString(HttpResponse.unauthorized("用户未登录"))
        );
    }

    private void onAuthenticationSuccess(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Authentication authentication)
            throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        SysUser sysUserAccount
                = service.findSysUserByUsername(
                user.getUsername());

        String token = jwtUtil.createJWT(
                user,
                String.valueOf(sysUserAccount.getId()),
                sysUserAccount.getUsername()
        );

        var uAuthorize = SysUserAuthorize.builder()
                .expireTime(jwtUtil.expireTime())
                .token(token)
                .build();

        BeanUtils.copyProperties(sysUserAccount, uAuthorize);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(
                objMapper.writeValueAsString(
                        HttpResponse.success(uAuthorize)
                )
        );
    }

    private void onAuthenticationFailure(HttpServletRequest request,
                                         HttpServletResponse response,
                                         AuthenticationException e)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(
                objMapper.writeValueAsString(
                        HttpResponse.unauthorized(e.getMessage()))
        );
    }

    private void onLogoutSuccess(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Authentication authentication)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        var writer = response.getWriter();
        var auth = request.getHeader("Authorization");
        if (jwtUtil.invalidateJWT(auth)) {
            writer.write(objMapper.writeValueAsString(HttpResponse.success()));
        } else {
            writer.write(objMapper.writeValueAsString(
                    HttpResponse.failure(400, "退出登录失败"))
            );
        }
    }
}
