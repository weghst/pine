package com.weghst.pine.web.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 4039674464998056300L;

    private String email;
    private String mobile;
    private String password;
    private boolean emailValid;
    private boolean mobileValid;
}
