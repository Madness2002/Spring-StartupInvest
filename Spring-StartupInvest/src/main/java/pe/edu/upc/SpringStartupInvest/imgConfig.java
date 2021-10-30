package pe.edu.upc.SpringStartupInvest;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configurable
public class imgConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/imagenes/**").addResourceLocations("file:/C:/Users/Usuario/Desktop/Programacion_en_web/Sstartup-invest_test/imagenes/");

	}
}
