package com.example.onlinebookstore.common;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

@Component
public class Response<T>  {
    private Integer code;
    private String msg;
    private T data;
    public String toJSON(Integer code,String msg,T data)
    {
        this.code=code;
        this.msg=msg;
        this.data=data;
        return JSON.toJSONString(this);
    }
    public String toJSON(Integer code,String msg)
    {
        this.code=code;
        this.msg=msg;
        this.data=null;
        return JSON.toJSONString(this);
    }
    public String toJSON(Integer code,T data)
    {
        this.code=code;
        this.msg=null;
        this.data=data;
        return JSON.toJSONString(this);
    }
    public String toJSON(Integer code)
    {
        this.code=code;
        this.msg=null;
        this.data=null;
        return JSON.toJSONString(this);
    }
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
