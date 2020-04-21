package william.eshop.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author zhangshenao
 * @Date 2019-12-17
 * @Description 文件处理相关工具类
 */
public class FileUtils {
    private static final Set<String> LEGAL_IMAGE_TYPES = new HashSet<>(Arrays.asList("png", "jpg", "jpeg"));

    /**
     * 是否为合法的图片文件
     */
    public static boolean isLegalImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            return false;
        }
        String[] names = fileName.split("\\.");

        // 获取文件的后缀名
        String fileType = names[names.length - 1];

        return isLegalImageType(fileType);
    }


    /**
     * 是否为合法的图片类型文件
     */
    public static boolean isLegalImageType(String fileType) {
        return LEGAL_IMAGE_TYPES.contains(fileType);
    }
}
