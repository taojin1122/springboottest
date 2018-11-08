package com.example.springbootest.study;

/**
 * 注入一个普通类
 *
 * 我们知道，一个项目一般会分为开发环境、测试环境和生产环境。OOS文件上传
 * 一般有如下几个参数：appKey、appSecret、bucket、endpoint等。不同环境的参数都可能
 * 不一样，这样便于区分。
 * 按照传统的做法，我们在代码里设置这些参数，这样做的话，每次发布不同的环境包都需要手动修改代码。
 *
 * 这个时候，我们就可以考虑这些参数定义到配置文件里面，通过@Value 注解取出来，再通过@Bean 将其
 * 定义为一个Bean，这时我们只需要在需要使用的地方注入该Bean即可。
 */
public class Aliyun {
      private String appKey;
      private String appSecret;
      private String bucket;
      private String endPoint;
      public static class Builder{
          private String appKey;
          private String appSecret;
          private String bucket;
          private String endPoint;

          public Builder setAppKey(String appKey){
              this.appKey = appKey;
              return this;
          }
          public Builder setAppSecret(String appSecret){
              this.appSecret = appSecret;
              return this;
          }
          public Builder setBucket(String bucket){
              this.bucket = bucket;
              return this;
          }
          public Builder setEndPoint(String endPoint){
              this.endPoint = endPoint;
              return this;
          }
          public Aliyun build(){
              return new Aliyun(this);
          }
      }
      public static Builder options(){
          return new Aliyun.Builder();
      }
      private Aliyun(Builder builder){
          this.appKey = builder.appKey;
          this.appSecret = builder.appSecret;
          this.bucket = builder.bucket;
          this.endPoint = builder.endPoint;
      }

    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getBucket() {
        return bucket;
    }

    public String getEndPoint() {
        return endPoint;
    }
}
