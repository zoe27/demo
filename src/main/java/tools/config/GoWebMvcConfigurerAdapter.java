/**
 * @Description: TODO
 * @author 作者 zhoubin
 * @version 创建时间：2019年9月15日 下午1:39:08
 * GoWebMvcConfigurerAdapter.java
 * @version V1.0
 * Copyright (c) 2019, zoe27@126.com All Rights Reserved.
 * @since JDK 1.8
 */
package tools.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import yiwang.salary.tools.os.EPlatform;
import yiwang.salary.tools.os.OSinfo;
import yiwang.salary.tools.util.PathUtil;


@Configuration
public class GoWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private String sourceFilePre = "file://";

    // 配置可访问的资源路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String resourcePath = PathUtil.resourcePath();
        String imageParentPath = sourceFilePre + resourcePath.replace("siteSearchEngine/", "") + "snapShotSite/";
        if (EPlatform.Linux == OSinfo.getOSname()) {
            imageParentPath = sourceFilePre + resourcePath + "snapshot/snapShotSite/";
        }
        //配置静态资源处理
        registry.addResourceHandler("/**")
                .addResourceLocations("resources/", "static/", "public/", "h5/",
                        "META-INF/resources/")
                .addResourceLocations("classpath:resources/", "classpath:static/","classpath:h5/",
                        "classpath:public/", "classpath:META-INF/resources/")
                .addResourceLocations(imageParentPath);
    }

    /**
     * set cross region
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
}

