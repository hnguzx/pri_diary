package per.guzx.pri_diary.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import per.guzx.pri_diary.enumeration.ErrorEnum;

import java.io.Serializable;

public class ApiResp<T> implements Serializable {

    /**
     * 正常响应码
     */
    private static final int SUCCESS_CODE = 200;
    /**
     * 正常响应信息
     */
    private static final String SUCCESS_MSG = "SUCCESS";

    /**
     * 错误码
     */
    private int code = SUCCESS_CODE;

    /**
     * 错误信息
     */
    private String msg = SUCCESS_MSG;

    /**
     * 响应内容，默认为null
     */
    private T data = null;

    /**
     * 是否正常的响应
     *
     * @return true为正常返回
     */
    @JsonIgnore
    public boolean isOk() {
        return code == SUCCESS_CODE;
    }

    /**
     * 无data的正常返回
     *
     * @return
     */
    public static ApiResp retOk() {
        return new ApiResp();
    }

    /**
     * 有data的正常返回
     *
     * @param data 正确信息
     * @param <T>  信息类型
     * @return
     */
    public static <T> ApiResp<T> retOk(T data) {
        ApiResp<T> response = new ApiResp<>();
        response.setData(data);
        return response;
    }

    /**
     * 无data的失败返回
     *
     * @param error 错误类型
     * @return
     */
    public static ApiResp retFail(ErrorEnum error) {
        return retFail(error.getCode(), error.getMsg());
    }

    /**
     * 有data的失败返回
     *
     * @param error 错误类型
     * @param data  详细错误信息
     * @param <T>
     * @return
     */
    public static <T> ApiResp<T> retFail(ErrorEnum error, T data) {
        return retFail(error.getCode(), error.getMsg(), data);
    }

    /**
     * 无data的失败返回
     *
     * @param code 错误码
     * @param msg  错误信息
     * @param <T>
     * @return
     */
    public static <T> ApiResp<T> retFail(int code, String msg) {
        ApiResp<T> response = new ApiResp<>();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    /**
     * 有data的失败返回
     *
     * @param code 错误码
     * @param msg  错误信息
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ApiResp<T> retFail(int code, String msg, T data) {
        ApiResp<T> response = new ApiResp<>();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
