package com.example.springbootest.apiVersion;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 接口版本控制
 *  一个系统上线后悔不断迭代更新，需求也会不断变化，有可能接口的参数也会发生变化，
 *  如果我在原有的参数上直接修改，可能会修改线上系统的正常运行，这事我们就需要设置不同的版本，这样
 *  即使参数发生变化，由于老版本没有变化，因此不会影响上线系统的运行。
 *
 *  一般我们可以在地址上带上版本号，也可以在参数上带上版本号，还可以在header 里带上版本号，这里我们在
 *  地址上带上版本号，大致的地址如：http://api.example.com/v1/test
 *  其中 v1 即代表的是版本号。
 *   1.@Retention(RetentionPolicy.RUNTIME)这种类型的注解将被JVM保留，所以他们能在运行时被JVM或其他使用反射机制的代码
 *     读取
 *    2.Cocumented 注解表名这个注解应该被javadoc工具记录。
 *    3.target 说明注解所修饰的对象范围：method 用户描述方法，type：用于描述类、接口或者enum声明
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {
    /**
     * 版本号
     * @return
     */
     int value();
}
