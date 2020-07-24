package com.mzx.webcocket.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mzx.webcocket.util.MessageUtils;
import com.mzx.webcocket.vo.Message;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhenXinMa
 * @date 2020/7/24 13:42
 */
@ServerEndpoint(value = "/chat",configurator = GetHttpSessionConfiguration.class)
@Component
public class ChatEndpoint {

    /*
     * --------------------------------------------------------
     * 每一个客户端都会有一个该类的对象.
     * --------------------------------------------------------
     * */

    /**
     * 存储每一个用户所对应的该类对象.
     */
    public volatile static Map<String, ChatEndpoint> onLineUsers = new ConcurrentHashMap<>();

    /**
     * 通过该对象可以发送消息给指定用户.
     */
    private Session session;

    /**
     * 获取用户名.
     */
    private HttpSession httpSession;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {


        this.session = session;
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        // 一个用户一个session.
        String user = (String) httpSession.getAttribute("user");
        System.out.println("用户连接成功 : " + user);

        // 将当前在洗用户装入用户集合里.
        onLineUsers.put(user, this);

        // 上线之后，还需要将自己登陆的消息发送给其他人.
        // 刚建立连接的时候,我应该将当前用户之前的在线用户发送过去.
        String message = MessageUtils.getMessage(true, getNames(1), null, setTransforList());
        // 消息推送
        sendMessageAllUsers(message);

    }

    @OnMessage
    public void onMessage(Session session, String message) {

        /*
         * --------------------------------------------------------
         * 将消息格式转换成Java类.
         * --------------------------------------------------------
         * */
        try {

            ObjectMapper mapper = new ObjectMapper();
            Message msg = mapper.readValue(message, Message.class);
            String toName = msg.getToName();
            String m = msg.getMessage();
            String username = (String) httpSession.getAttribute("user");
            String resultMessage = MessageUtils.getMessage(false, m, username, setTransforList());
            // 这里有可能获取不到值。。。 这个是什么情况
            ChatEndpoint endpoint = onLineUsers.get(toName);
            // 获取接受消息的人的endpoint。
            endpoint.session.getBasicRemote().sendText(resultMessage);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @OnClose
    public void onClose(Session session) {

        String username = (String) httpSession.getAttribute("user");
        System.out.println("移除用户: " + username);
        onLineUsers.remove(username);
        // 向所有用户说明我已经下线了
        String message = MessageUtils.getMessage(true, getNames(2), null, setTransforList());
        // 发送全体
        sendMessageAllUsers(message);
    }


    private void sendMessageAllUsers(String message) {


        // 实现将该消息推送给所有在线用户.
        Set<String> keySet = onLineUsers.keySet();
        keySet.forEach(item -> {

            ChatEndpoint chatEndpoint = onLineUsers.get(item);
            try {

                chatEndpoint.session.getBasicRemote().sendText(message);
            } catch (IOException e) {

                e.printStackTrace();
            }
        });

    }


    private String getNames(int flag) {

        /*
         * --------------------------------------------------------
         * 您的好友张三上线了.
         * 您的好友张三下面了.
         * --------------------------------------------------------
         * */

        // 都是系统消息.
        // 上线或者下线的用户名字.
        String user = (String) httpSession.getAttribute("user");
        if (flag == 1) {

            // 用户上线
            return "您的好友: " + user + "上线了";
        } else {

            // 用户下线.
            return "您的好友: " + user + "下线了";
        }


    }

    private List<String> setTransforList() {

        Set<String> keySet = onLineUsers.keySet();
        List<String> list = new ArrayList<>();
        for (String s : keySet) {
            list.add(s);
        }
        return list;
    }

    public Session getSession() {
        return session;
    }
}
