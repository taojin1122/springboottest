package com.example.springbootest.study;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import com.example.springbootest.apiVersion.CustomRequestMappingHandlerMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

/**
 *  下面配置Bean之后，在需要输入Bean的地方，
 *  @Autowired
 *  private Aliyun aliyun；
 */
@SpringBootConfiguration
public class WebConfig extends WebMvcConfigurationSupport{
    @Value("${appKey}")
     private String appKey;
    @Value("${appSecret}")
    private String appSecret;
    @Value("${bucket}")
    private String bucket;
    @Value("${endPoint}")
    private String endPoint;

    @Bean
    public Aliyun aliyun(){
        return Aliyun.options()
                .setAppKey(appKey)
                .setAppSecret(appSecret)
                .setBucket(bucket)
                .setEndPoint(endPoint)
                .build();
    }
    @Bean
    public ApiInterceptor interceptor(){
        return new ApiInterceptor();
    }
    /**
     * 在 @SpringBootConfiguratio 注解的类继承 WebMvcConfinurationSupport 类，
     * 并重写addInterceptors 方法，将AliInterceptor 拦截器类添加进去。
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        //添加拦截器
        registry.addInterceptor(new ApiInterceptor());
    }

    /**
     * 版本接口控制
     * @return
     */
    @Bean
    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
       RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
       handlerMapping.setOrder(0);
       handlerMapping.setInterceptors(getInterceptors());
        return handlerMapping;
    }

    /**
     *    Spring Boot 中RestControler 返回的字符串默认使用Jackson引擎，它也提供了工厂类，
     *    我们可以自定义JSON引擎，本节实例我们将JSON引擎替换fastJSON，首先需要引入fastJSON:
     * @param converters
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        /*
         1.需要先定义一个convert转换消息的对象；
         2.添加fastjson的配置信息，比如是否需要格式化范湖的json数据
         3.在convert 中添加配置信息
         4.将convert 添加到converters中
         */
        //1.定义一个convert转换消息对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2.添加fastjson的配置信息，比如：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }
}
