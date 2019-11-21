package william.eshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author zhangshenao
 * @Date 2019-11-19
 * @Description API服务启动类
 */
@SpringBootApplication
@MapperScan("william.eshop.mapper") //扫描MyBatis通用Mapper所在的包
public class EShopAPIApplication {
    public static void main(String[] args) {
        SpringApplication.run(EShopAPIApplication.class, args);
    }
}
