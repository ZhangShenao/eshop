package william.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description 跨域配置
 */
@Configuration
public class CorsConfig {
    //配置跨域Filter
    @Bean
    public CorsFilter corsFilter() {
        //1.添加Cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");   //设置允许的origin
        config.setAllowCredentials(true);   //设置是否发送cookie信息
        config.addAllowedMethod("*");   //设置允许的请求方式
        config.addAllowedHeader("*");        //设置允许的header

        //2.设置Filter的映射路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        //3.返回定义好的CorsFilter
        return new CorsFilter(source);
    }
}
