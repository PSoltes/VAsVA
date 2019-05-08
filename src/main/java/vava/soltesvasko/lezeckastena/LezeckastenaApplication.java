package vava.soltesvasko.lezeckastena;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class LezeckastenaApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(LezeckastenaApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/picture/**").addResourceLocations("file:///C:/Users/PavolSoltes/Desktop/VAsVA/images/")
                .setCachePeriod(0);
    }

}
