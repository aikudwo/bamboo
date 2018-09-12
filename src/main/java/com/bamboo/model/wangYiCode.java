package com.bamboo.model;

public class wangYiCode {
    /**
     * 验证码id
     */
    private String captchaId;
    /**
     * 提交二次校验的验证数据，即NECaptchaValidate值
     */
    private String validate;
    /**
     * 用户信息，值可为空
     */
    private String user;
    /**
     * 密钥对id
     */
    private String secretId;
    /**
     * 版本信息，固定值v2
     */
    private String version = "v2";
    /**
     * 当前时间戳的毫秒值，例1480395193000
     */
    private String timestamp = String.valueOf(System.currentTimeMillis());
    /**
     * 随机正整数，与 timestamp 联合起来，用于防止重放攻击
     */
    private String none;
    /**
     * 签名信息，见签名计算
     */
    private String signature;

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNone() {
        return none;
    }

    public void setNone(String none) {
        this.none = none;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
