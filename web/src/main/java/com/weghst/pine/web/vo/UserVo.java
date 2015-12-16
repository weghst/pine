package com.weghst.pine.web.vo;

import lombok.Data;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Data
public class UserVo {

    private String email;
    private String password;
    private boolean emailValid;
    private int age;
}
