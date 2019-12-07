package william.eshop.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    public static Optional<String> getCookieValue(HttpServletRequest request, String cookieName) {
        return doGetCookieValue(request, cookieName, false);
    }

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

    private static Optional<String> doGetCookieValue(HttpServletRequest request, String cookieName, boolean encoded) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return Optional.empty();
        }
        Optional<Cookie> cookie = Arrays.stream(cookies).filter(c -> c.getName().equals(cookieName)).findAny();
        if (!cookie.isPresent()) {
            return Optional.empty();
        }
        String value = cookie.get().getValue();
        if (encoded) {
            try {
                value = URLDecoder.decode(value, DEFAULT_CHARSET);
            } catch (UnsupportedEncodingException e) {
                log.error("Decode Cookie Error! ", e);
                return Optional.empty();
            }
        }
        return Optional.of(value);
    }
}
