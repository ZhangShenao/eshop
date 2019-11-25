package william.eshop.utils;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhangshenao
 * @Date 2019-11-25
 * @Description 处理Cookie的工具类
 */
@Slf4j
public class CookieUtils {
    private static final int DEFAULT_EXPIRE = -1;
    private static final String DEFAULT_PATH = "/";
    private static final String DEFAULT_CHARSET = "utf-8";

    public static void setCookie(HttpServletResponse response, String cookieKey,
            String cookieValue) {
        doSetCookie(response, cookieKey, cookieValue, DEFAULT_PATH, DEFAULT_EXPIRE, false);
    }

    public static void removeCookie(HttpServletResponse response, String cookieKey) {
        doSetCookie(response, cookieKey, null, DEFAULT_PATH, DEFAULT_EXPIRE, false);
    }

    private static void doSetCookie(HttpServletResponse response, String cookieKey,
            String cookieValue, String path, int maxAge, boolean encoded) {
        try {
            if (encoded) {
                cookieValue = URLEncoder.encode(cookieValue, DEFAULT_CHARSET);
            }
            Cookie cookie = new Cookie(cookieKey, cookieValue);
            cookie.setMaxAge(maxAge);
            cookie.setPath(path);
            response.addCookie(cookie);
        } catch (Exception e) {
            log.error("Set Cookie Error! ", e);
        }
    }
}
