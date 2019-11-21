package william.eshop.utils;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description MD5加解密工具类
 */
@Slf4j
public class MD5Utils {
    public static String encode(String raw) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return Base64.encodeBase64String(md5.digest(raw.getBytes()));
        } catch (Exception e) {
            log.error("MD5 Encode Error! raw: {}", raw, e);
            return raw;
        }
    }
}
