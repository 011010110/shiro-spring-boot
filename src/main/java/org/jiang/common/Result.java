package org.jiang.common;

import java.util.Optional;

/**
 * @author Li.Linhua
 * @description 返回前台数据
 * @Date 2019/7/18
 */
public class Result {

    private String code;

    private String msg;

    private boolean status;

    private Object data;

    public Result(){

    }

    public Result(String code,String msg,boolean status,Object data){
        this.code = code;
        this.msg = msg;
        this.status = status;
        this.data = data;
    }
    public static Result success(){
        return new Result("0","成功",true,"");
    }

    public static Result error(){
        return new Result("1","失败",false,"");
    }

    public String getCode() {
        return code;
    }

    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public Result setStatus(boolean status) {
        this.status = status;
        return this;
    }


}
