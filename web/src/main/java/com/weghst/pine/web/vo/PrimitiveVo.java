package com.weghst.pine.web.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Data
public class PrimitiveVo implements Serializable {

    private static final long serialVersionUID = 5750350718186087188L;

    private Object value;

    public PrimitiveVo() {
    }

    public PrimitiveVo(Object value) {
        this.value = value;
    }
}
