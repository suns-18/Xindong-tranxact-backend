package neu.xindong.xact.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import neu.xindong.xact.dto.response.InfoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.SecureRandomSpi;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/ws/infoMessage/{role}")
@Component
public class InfoMessageServer {
    InfoMessage currentMsg;
    private static final Map<String, Session> subscriberMap =
            new ConcurrentHashMap<>();
    private static final Map<String, Session> senderMap =
            new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("role") String role) throws IOException {
        switch (role) {
            case "subscriber" -> subscriberHandler(session);
            case "sender" -> senderHandler(session);
        }
    }

    private void subscriberHandler(Session session) {
        subscriberMap.put(session.getId(), session);
        if (currentMsg == null) {
            currentMsg = InfoMessage.builder()
                    .code(0)
                    .message("证券市场又度过了平静的一瞬，" +
                            "欢迎您使用信东XAct证券业务综合服务平台！" +
                            "爱东，信东，等东！")
                    .breaking(false)
                    .build();
        }
        System.out.println(currentMsg.hashCode());
        sendMessage(session, currentMsg);
    }

    private void senderHandler(Session session) {
        senderMap.put(session.getId(), session);
    }

    @OnClose
    public void onClose(Session session) {
        subscriberMap.remove(session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session,
                          @PathParam("role") String role) {
        if (role.equals("operator")) return;
        var objectMapper = new ObjectMapper();
        try {
            currentMsg = objectMapper.readValue(message, InfoMessage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(currentMsg.hashCode());
        sendMessageToAllSubscribers(message);
    }

    private void sendMessage(Session session, InfoMessage msg) {
        var objMapper = new ObjectMapper();
        try {
            var jsonString = objMapper.writeValueAsString(msg);
            session.getBasicRemote().sendText(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessageToAllSubscribers(String senderMsg) {
        try {
            subscriberMap.forEach((k, v) -> {
                try {
                    v.getBasicRemote().sendText(senderMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}