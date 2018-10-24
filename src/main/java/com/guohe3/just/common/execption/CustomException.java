package com.guohe3.just.common.execption;


import com.guohe3.just.common.enums.ResultEnum;

/**
 * @author Ethanp
 * @date 2018/1/29 10:20
 * 自定义异常类
 */
public class CustomException extends RuntimeException {
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CustomException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
