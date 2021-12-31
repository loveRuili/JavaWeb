package com.czxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult {

    private Integer code;
    private String msg;
    private Object data;
    private Integer total;
    public static BaseResult success(Object data){
        return new BaseResult(0,"成功",data);
    }
    public static BaseResult success(Object data,Integer total){
        return new BaseResult(0,"成功",data,total);
    }
    public static BaseResult fail(){
        return new BaseResult(1,"失败",null);
    }

    public BaseResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
