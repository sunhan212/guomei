package com.youlishu.config;

import com.youlishu.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;


@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getTokenInterceptor(){
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //TokenInterceptor
        registry.addInterceptor(getTokenInterceptor()).excludePathPatterns("/login/**");
    }

    @Override
    public void configurePathMatch(final PathMatchConfigurer configurer) {

    }

    @Override
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {

    }

    @Override
    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {

    }

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {

    }

    @Override
    public void addFormatters(final FormatterRegistry registry) {

    }



    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {

    }

    @Override
    public void addCorsMappings(final CorsRegistry registry) {

    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {

    }

    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {

    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {

    }

    @Override
    public void addReturnValueHandlers(final List<HandlerMethodReturnValueHandler> returnValueHandlers) {

    }

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void configureHandlerExceptionResolvers(final List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public void extendHandlerExceptionResolvers(final List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}