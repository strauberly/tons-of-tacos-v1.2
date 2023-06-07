package com.adamstraub.tonsoftacos.tonsoftacos;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


@SpringBootApplication
public class TonsOfTacosApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

//	@Bean
//	HandlerExceptionResolver handlerExceptionResolver() {
//		return new HandlerExceptionResolver() {
//			@Override
//			public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//				return null;
//			}
//		};
//
//	}
	public static void main (String[]args){
		SpringApplication.run(TonsOfTacosApplication.class, args);
	}
}


