package com.example.springbootest.apiVersion;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping{

     private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion){
         return apiVersion == null ? null : new ApiVersionCondition(apiVersion.value());
     }
    @Nullable
    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType,ApiVersion.class);
        return createCondition(apiVersion);
    }

    @Nullable
    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
         ApiVersion apiVersion = AnnotationUtils.findAnnotation(method,ApiVersion.class);
        return createCondition(apiVersion);
    }
}
