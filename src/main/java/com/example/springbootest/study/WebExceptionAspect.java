package com.example.springbootest.study;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  异常处理
 *    我们在Controller里提供接口，通常需要捕捉异常，并进行友好的提示，否则一旦出错，界面上就会显示报错信息，
 *    给用户一种不好的体验。
 *    最简单的方法就是每个方法都使用try catch 进行捕捉，报错后，则在catch里面设置友好的报错提示。
 *    如果方法很多，每个都需要try catch，代码会显得臃肿，写起来也比较麻烦。
 *
 *    现在我们提供一个公共的入口进行统一的异常处理。下面通过Spring 的AOP的特性就可以很方便的实现异常的统一处理。
 */
@Aspect
@Component
public class WebExceptionAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebExceptionAspect.class);
    //凡是注解的RequestMapping的方法都被拦截
    // 一旦报错，则会执行 handleThrowing 方法
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void webPointcut(){

    }

    /**
     *  拦截web异常，记录异常日志，并返回友好信息到前端
     *  目前只连接Exception，是否拦截Error再做考虑
     * @param e
     */
    @AfterThrowing(pointcut="webPointcut()",throwing="e")
    public void handleThrowing(Exception e){
         /* e.printStackTrace();
          logger.error("发现异常！"+e.getMessage());
          logger.error(JSON.toJSONString(e.getStackTrace()));
          //这里输入友好性信息
        writeContent("出现异常");*/
         logger.debug("exception 来了！");
         if(StringUtils.isNotBlank(e.getMessage())){
              writeContent(e.getMessage());
         }else{
             writeContent("参数错误！");
         }
    }

    /**
     * 将内容输出到浏览器
     * @param content
     */
    private void writeContent(String content){
       HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
       response.reset();
       response.setCharacterEncoding("UTF-8");
       response.setHeader("Content-Type","text/plain;character=UTF-8");
       response.setHeader("ico-content-type","exception");
        PrintWriter writer = null;
        try {
             writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(content);
        writer.flush();
        writer.close();
    }

}
