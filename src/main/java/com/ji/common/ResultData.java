package com.ji.common;

/**
 * ClassName: ResultData
 * Description:
 * date: 2019/4/30 16:40
 *
 * @author ji
 * @version 1.0
 * @since JDK 1.8
 */
public class ResultData {
    private int code;
    private Object data;
    private String message;

    public ResultData(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
