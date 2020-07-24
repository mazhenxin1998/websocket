package com.mzx.webcocket.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mzx.webcocket.util.MessageUtils;
import com.mzx.webcocket.vo.Message;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author ZhenXinMa
 * @date 2020/7/24 22:09
 */
@ServerEndpoint(value = "/server/system/put")
@Component
public class SystemPutMessageHandler {


    private Session session;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {

        System.out.println("管理员建立了连接.");
        this.session = session;

    }

    @OnMessage
    public void onMessage(Session session, String message) {

        /*
         * --------------------------------------------------------
         * 不需要定义什么消息格式,直接发送来的消息直接显示即可.
         * --------------------------------------------------------
         * */

        // 先将Message转换我们自己的Message
        try {

            ObjectMapper mapper = new ObjectMapper();
            Message msg = mapper.readValue(message, Message.class);
            // 现在需要将该消息发送给所有在线用户
            Map<String, ChatEndpoint> onLineUsers = ChatEndpoint.onLineUsers;
            Set<String> keySet = onLineUsers.keySet();
            keySet.forEach(key -> {

                ChatEndpoint endpoint = onLineUsers.get(key);
                // 发送消息我的给他指定一个特殊的关键字
                String content = "500," + msg.getMessage();
                String m = MessageUtils.getMessage(true, content, null, null);
                try {

                    endpoint.getSession().getBasicRemote().sendText(m);
                } catch (IOException e) {

                    e.printStackTrace();
                }

            });

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @OnClose
    public void onClose(Session session) {


    }


}
