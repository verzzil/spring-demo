package ru.itis.springdemo.services.messages;

import ru.itis.springdemo.dto.Message;
import ru.itis.springdemo.dto.UserDto;

import java.util.HashMap;
import java.util.List;

public interface MessagesService {
    void saveToDb(Message message);
    HashMap<Integer, List<Message>> getDialogs(Integer currentUserId, List<UserDto> chattingUsers);
}