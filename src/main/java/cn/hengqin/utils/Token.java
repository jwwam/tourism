/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hengqin.utils;

/**
 *
 * @author dengqingjiang
 */
public class Token {

    private String clientId;//业务系统标识
    private String puserID;//帐号唯一标识
    private String account; //统一帐号
    private String expireTime; //过期时间
    private String authType; //认证方式
    private String resultCode ;

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the puserID
     */
    public String getPuserID() {
        return puserID;
    }

    /**
     * @param puserID the puserID to set
     */
    public void setPuserID(String puserID) {
        this.puserID = puserID;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the authType
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * @param authType the authType to set
     */
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    /**
     * @return the expireTime
     */
    public String getExpireTime() {
        return expireTime;
    }

    /**
     * @param expireTime the expireTime to set
     */
    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * @return the resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode the resultCode to set
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }


}
