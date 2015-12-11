package com.weghst.pine.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Data
public class UserVo {

    /**
     * Email.
     */
    private String email;
    /**
     * Password.
     */
    private String password;
    /**
     * HELLO.
     */
    private List<String> keys;
    /**
     * Map.
     */
    private Map<String, Object> values;

}
