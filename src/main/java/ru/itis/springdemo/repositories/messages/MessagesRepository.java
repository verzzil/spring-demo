package ru.itis.springdemo.repositories.messages;

import ru.itis.springdemo.dto.Message;
import ru.itis.springdemo.repositories.CrudRepository;

import java.util.List;

public interface MessagesRepository extends CrudRepository<Message> {
    List<Message> getDialog(Integer idFromUser, Integer idToUser);
}
