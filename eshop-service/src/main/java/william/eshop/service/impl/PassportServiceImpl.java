package william.eshop.service.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import william.eshop.constants.UserConstants;
import william.eshop.service.service.PassportService;
import william.eshop.utils.CookieUtils;

/**
 * @Author zhangshenao
 * @Date 2019-12-07
 * @Description
 */
@Service
public class PassportServiceImpl implements PassportService {
    @Override
    public boolean isLogin(HttpServletRequest request) {
        Optional<String> userId = parseUserIdFromCookie(request);
        return (userId.isPresent() && !StringUtils.isEmpty(userId.get()));
    }

    @Override
    public Optional<String> parseUserIdFromCookie(HttpServletRequest request) {
        return CookieUtils.getCookieValue(request, UserConstants.USER_ID_COOKIE_KEY);
    }
}
