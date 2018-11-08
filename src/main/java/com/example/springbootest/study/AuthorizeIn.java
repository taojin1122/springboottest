package com.example.springbootest.study;



import javax.validation.constraints.NotBlank;

/**
 *  后端数据合法性的校验，为了接口的健壮性，我们通常除了客户端进行输入合法性校验外，在
 *  controller 的方法里，我们也需要对参数进行哈法性家宴，传统的做法是每个方法的参数都做
 *  一遍判断，这种方式不太优雅，也不易维护
 */
public class AuthorizeIn  {
    /**
     * notBlank 注解只能租用在String 上，不能为null ，而且调用trim（） 后，长度必须大于0
     * message 属性是参数验证返回的结果
     *
     * notBlank 注解必须配合Valid 使用
     *
     *
     */
    @NotBlank(message="缺少response_type参数")
    private String responseType;
    @NotBlank(message="缺少client_id参数")
    private String ClientId;

    private String state;
    @NotBlank(message="缺少redirect_uri参数")
    private String redirectUri;

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}

























































