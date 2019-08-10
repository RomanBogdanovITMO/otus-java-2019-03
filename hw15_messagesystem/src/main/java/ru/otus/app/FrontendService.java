package ru.otus.app;

import org.springframework.stereotype.Component;
import ru.otus.dataset.UserDataSet;
import ru.otus.messageSystem.Addressee;

import javax.websocket.Session;
@Component
public interface FrontendService extends Addressee {
    void handleRequest(int sessionId, String message);

    void handleAddUserRequest(int sessionId, String userName, int age);
    void handleAddUserResponse(int sessionId, UserDataSet user);

    void handleGetUserInfoRequest(int sessionId, long userId);
    void handleGetUserInfoResponse(int sessionId, UserDataSet user);

    int addSession(Session session);
    void removeSession(int sessionId);
}
