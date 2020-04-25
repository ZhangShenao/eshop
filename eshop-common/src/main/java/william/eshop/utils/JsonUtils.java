package william.eshop.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author zhangshenao
 * @Date 2020-04-25
 * @Description json相关的工具类
 */
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseFrom(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
