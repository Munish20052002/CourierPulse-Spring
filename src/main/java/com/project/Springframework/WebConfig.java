
package com.project.Springframework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionCheckInterceptor sessionCheckInterceptor;
   
    

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	
        registry.addInterceptor(sessionCheckInterceptor)
                .addPathPatterns("/*") // Apply to all paths under '/myapp'
                .excludePathPatterns("/loginForm"); // Exclude login paths
        
    }
}