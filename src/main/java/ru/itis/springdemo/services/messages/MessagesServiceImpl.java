package ru.itis.springdemo.services.messages;

import ru.itis.springdemo.dto.Message;
import ru.itis.springdemo.dto.UserDto;
import ru.itis.springdemo.repositories.messages.MessagesRepository;

import java.util.HashMap;
import java.util.List;

public class MessagesServiceImpl implements MessagesService {

    private MessagesRepository messagesRepository;

    public MessagesServiceImpl(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public void saveToDb(Message message) {
        messagesRepository.save(message);
    }

    @Override
    public HashMap<Integer, List<Message>> getDialogs(Integer currentUserId, List<UserDto> chattingUsers) {
        HashMap<Integer, List<Message>> dialogs = new HashMap<>();
        for(UserDto user : chattingUsers) {
            dialogs.put(
                    user.getId(),
                    messagesRepository.getDialog(currentUserId, user.getId())
            );
        }
        return dialogs;
    }
}
