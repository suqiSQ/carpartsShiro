package com.yitian.sq.vo;

import org.springframework.stereotype.Component;

@Component
public class UserVo {
    private String loginName;
    private String password;
    private String validate;
    private String email;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", validate='" + validate + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
