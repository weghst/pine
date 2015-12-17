package com.weghst.pine.web.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Data
public class IdentifierVo implements Serializable {

    private static final long serialVersionUID = 5750350718186087188L;

    private Object value;

    public IdentifierVo() {
    }

    public IdentifierVo(Object value) {
        this.value = value;
    }
}
