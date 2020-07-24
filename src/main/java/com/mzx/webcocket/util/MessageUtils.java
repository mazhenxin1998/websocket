package com.mzx.webcocket.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mzx.webcocket.vo.ResultMessage;

import java.util.List;

/**
 * 用于封装消息的工具类.
 *
 * @author ZhenXinMa
 * @date 2020/7/24 11:45
 */
public class MessageUtils {

    public static String getMessage(boolean isSystem, Object message, String formName, List<String> friendList) {

        try {

            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setSystem(isSystem);
            resultMessage.setMessage(message);
            if (friendList != null) {

                resultMessage.setFriendList(friendList);
            }
            if (formName != null) {

                resultMessage.setFromName(formName);
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(resultMessage);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


}
