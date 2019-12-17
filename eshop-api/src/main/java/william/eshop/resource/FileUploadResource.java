package william.eshop.resource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-12-17
 * @Description 文件上传配置资源
 */
@Component
@ConfigurationProperties(prefix = "upload")
@PropertySource("classpath:file-upload.properties")  //绑定配置文件
@Data
public class FileUploadResource {
    private String basePath;

    private String imageServerUrl;
}
