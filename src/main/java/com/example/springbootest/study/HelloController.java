package com.example.springbootest.study;

import com.example.springbootest.apiVersion.ApiVersion;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Spring boot 整个应用程序只有一个配置文件，那就是.propertis 或.yml文件。但是，在前面的示例中，我们并没有看到该配置文件。
 * 那是因为Spring boot 对每个配置项都有默认值，当然我们也可以添加配置文件，用来覆盖默认值，这里以.properties文件为例，首先在resources
 * 下建一个名为application.properties(z注意：文件名必须是application)的文件，键入内容为：
 *
 *
 *
 */
// restController 返回的是一个json格式的数据
@RestController
public class HelloController extends SpringBootServletInitializer{
    /**
     *    通过ApiVersion 实现了版本控制，如果增加了一个版本，则创建一个新的Controller，方法名一致，
     *    ApiVersion设置为2 ，则地址中v1 会找到ApiVersion 为1 的方法，v2 会找到ApiVersion为 2 的方法
     * @return
     */
    @ApiVersion(1)
     @RequestMapping("{version}/hello")
    public String hello(){
          return "hello world";
     }


    /**
     * maven项目打包的时候需要写下面方法
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(HelloController.class);
    }

    /**
     *  GetMapping 是一个组合注解，是requetMapping（method=“RequestMethod.GET”）的缩写.
     *   在Controller 的方法需要校验的参数后面必须跟BindingResult，否则无法进行校验。
     *   但是这样会抛出异常，对用户而言不太友好。我们可以进一步对异常进行拦截。
     * @param authorize
     * @param ret
     */
    @GetMapping("authorize")
    public void authorize(@Valid AuthorizeIn authorize, BindingResult result){
        if(result.hasFieldErrors()){
             List<FieldError> errorList = result.getFieldErrors();
             //通过断言抛出参数不合法的异常
            errorList.stream().forEach(item -> Assert.isTrue(false,item.getDefaultMessage()));
        }
    }

    /**
     * 把验证的代码单独封装成方法，这样每次参数校验，只需要调用validate方法就可以了。
     *
     * @param result
     */
    protected void validate(BindingResult result){
         if(result.hasFieldErrors()){
             List<FieldError> errorList = result.getFieldErrors();
             errorList.stream().forEach(item -> Assert.isTrue(false,item.getDefaultMessage()));
         }
    }
}
