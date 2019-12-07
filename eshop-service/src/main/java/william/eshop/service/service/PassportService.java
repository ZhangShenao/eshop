package william.eshop.service.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zhangshenao
 * @Date 2019-12-07
 * @Description 用户登录/注册服务
 */
public interface PassportService {
    /**
     * 通过Cookie判断用户是否已登录
     */
    boolean isLogin(HttpServletRequest request);

    /**
     * 从Cookie中解析用户id
     */
    Optional<String> parseUserIdFromCookie(HttpServletRequest request);
}
