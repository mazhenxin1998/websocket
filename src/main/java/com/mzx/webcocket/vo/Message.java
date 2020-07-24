package com.mzx.webcocket.vo;

/**
 * 客户端向服务端发送消息的封装.
 *
 * @author ZhenXinMa
 * @date 2020/7/24 11:30
 */
public class Message {

    private String toName;
    private String message;

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
