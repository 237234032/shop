package com.mz.shop.module.sys.dto;

import java.io.Serializable;

/**
 * @Title:
 * @Description:
 * @author: chris
 * @version: 1.0.0
 * @date: 2018/01/09 11:39
 */
public class LoginDTO implements Serializable {
    private String loginID;
    private String loginPWD;
    private String remember;
    private String validateCode;

    public LoginDTO() {
    }

    public LoginDTO(String loginID, String loginPWD, String remember,String validateCode) {
        this.loginID = loginID;
        this.loginPWD = loginPWD;
        this.remember = remember;
        this.validateCode = validateCode;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getLoginPWD() {
        return loginPWD;
    }

    public void setLoginPWD(String loginPWD) {
        this.loginPWD = loginPWD;
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "loginID='" + loginID + '\'' +
                ", loginPWD='" + loginPWD + '\'' +
                ", remember='" + remember + '\'' +
                ", validateCode='" + validateCode + '\'' +
                '}';
    }
}
