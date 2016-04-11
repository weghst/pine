package com.weghst.pine.domain;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class User implements Serializable {

    private static final long serialVersionUID = 8302778139312618271L;

    private long id;
    private String mobile;
    private String email;
    private String password;
    private boolean mobileValid;
    private boolean emailValid;
    private boolean enabled;
    private long createdTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMobileValid() {
        return mobileValid;
    }

    public void setMobileValid(boolean mobileValid) {
        this.mobileValid = mobileValid;
    }

    public boolean isEmailValid() {
        return emailValid;
    }

    public void setEmailValid(boolean emailValid) {
        this.emailValid = emailValid;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("mobile", mobile)
                .add("email", email)
                .add("password", password)
                .add("mobileValid", mobileValid)
                .add("emailValid", emailValid)
                .add("enabled", enabled)
                .add("createdTime", createdTime)
                .toString();
    }
}
