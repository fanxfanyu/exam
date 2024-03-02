package top.fanxfan.core.config;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * sa-token配置
 *
 * @author fanxfan
 */
@Slf4j
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML));
        converters.add(converter);
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }

    /**
     * 主要进行跨域配置
     */
    @Bean
    public SaServletFilter saServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude("/favicon.ico", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/**")
                .setAuth(obj -> SaManager.getLog().debug("--- path {},token {}", SaHolder.getRequest().getRequestPath(), StpUtil.getTokenValue()))
                .setError(e -> {
                    log.error("sa-token throw exception {}", e.getMessage());
                    return ResponseEntity.internalServerError().body(e.getMessage());
                })
                .setBeforeAuth(obj -> {
                            SaHolder.getResponse()
                                    .setHeader("Access-Control-Allow-Origin", "*")
                                    .setHeader("Access-Control-Allow-Methods", "*")
                                    .setHeader("Access-Control-Allow-Headers", "*")
                                    .setHeader("Access-Control-Max-Age", "3600");
                            SaRouter.match(SaHttpMethod.OPTIONS)
                                    .free(r -> {
                                    })
                                    .back();
                        }
                );
    }
}
