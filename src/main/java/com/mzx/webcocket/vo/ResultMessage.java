package com.mzx.webcocket.vo;

import java.util.List;

/**
 * 服务端发送给客户端的消息实体.
 *
 * @author ZhenXinMa
 * @date 2020/7/24 11:42
 */
public class ResultMessage {

    private boolean isSystem;
    private String fromName;
    /**
     * 如果是系统消息则message是数组.
     */
    private Object message;

    private List<String> friendList;

    public List<String> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<String> friendList) {
        this.friendList = friendList;
    }

    public boolean getSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
