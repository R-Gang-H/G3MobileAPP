package com.alicloud.oss.model;

/**
 * Created by jingdan on 2017/9/6.
 */

public class StsModel {
    /**
     * status : 200
     * AccessKeyId : STS.LC2TQvzG9qQF9N3K1Ayybmt9m
     * AccessKeySecret : BFYwEZiMNAnhiHvFtvD1ZRRrMERnkH7vGmfSHuwZBXEU
     * SecurityToken : CAISkwJ1q6Ft5B2yfSjIrYCHH+vCl5gYxpOtO2iCrzEUdfZOgrGSjzz2IHtEdXdvAu0csf80lWxR6/YSlqB6T55OSAmcNZIoW3WUMYLiMeT7oMWQweEuuv/MQBquaXPS2MvVfJ+OLrf0ceusbFbpjzJ6xaCAGxypQ12iN+/m6/Ngdc9FHHP7D1x8CcxROxFppeIDKHLVLozNCBPxhXfKB0ca3WgZgGhku6Ok2Z/euFiMzn+Ck79F9tStfMD9NJk1ZMoiAu3YhrImKvDztwdL8AVP+atMi6hJxCzKpNn1ASMKuEXXbbWPr4cwd1QoOvFnS/5e3/H4lOxlvOvIjJjwyBtLMuxTXj7WWIe62szAFfM+8GvT/Ac+UBqAAV3uNo8jObh/h4BD5Iy1Rta4lQzX99GAWmpqM+3geIFixeeB0/2qbwIkCfoB7tsEz+I6pDs+btynwLr5r+h+c/BwX9YJSbjzkrI2gDwYGtOCoV4KUaVZFl+Ix5zCOSuz6A0jreZ9BdOUoqvf5IqNGGrJbLOuITLqggyyOGXDLndQ
     * Expiration : 2017-09-18T12:51:04Z
     */

    private String status;
    private String AccessKeyId;
    private String AccessKeySecret;
    private String SecurityToken;
    private String Expiration;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccessKeyId() {
        return AccessKeyId;
    }

    public void setAccessKeyId(String AccessKeyId) {
        this.AccessKeyId = AccessKeyId;
    }

    public String getAccessKeySecret() {
        return AccessKeySecret;
    }

    public void setAccessKeySecret(String AccessKeySecret) {
        this.AccessKeySecret = AccessKeySecret;
    }

    public String getSecurityToken() {
        return SecurityToken;
    }

    public void setSecurityToken(String SecurityToken) {
        this.SecurityToken = SecurityToken;
    }

    public String getExpiration() {
        return Expiration;
    }

    public void setExpiration(String Expiration) {
        this.Expiration = Expiration;
    }
}