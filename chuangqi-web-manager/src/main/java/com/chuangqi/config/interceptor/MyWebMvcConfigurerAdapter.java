package com.chuangqi.config.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

/**
 *配置
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter{
	
	@Value("${project.welcome.file}")
	private String projectWelcomeFile;

	@Value("upload.file.base.dir")
    private String imageBasePath ;
	
    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
    	// 首页默认访问文件
        registry.addViewController("").setViewName(projectWelcomeFile);
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    } 
    
    /* (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	//登录拦截的管理器
        InterceptorRegistration registration=registry.addInterceptor(new LoginHandlerInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/common/**");
    	super.addInterceptors(registry);
    }
}
