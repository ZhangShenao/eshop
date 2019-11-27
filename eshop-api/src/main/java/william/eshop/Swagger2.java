package william.eshop;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description Swagger2配置
 */
@Configuration
@EnableSwagger2 //开启Swagger2功能 文档页地址:http://localhost:8888/swagger-ui.html
public class Swagger2 {
    @Bean
    public Docket createApi() {
        return new Docket(SWAGGER_2)    //指定api类型为swagger2
                .apiInfo(apiInfo())     //用于定义api文档汇总信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("william.eshop.controller"))  //指定Controller所在的包
                .paths(PathSelectors.any())     //包含所有controller
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("电商系统接口文档")      //文档页标题
                .version("1.0")             //文档版本
                .contact(contact())         //联系人信息
                .build();
    }

    private Contact contact() {
        return new Contact("zhangshenao", "https://github.com/ZhangShenao", "zhangshenao@qq.com");
    }
}
