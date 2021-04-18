package ru.itis.springdemo.dao.repositories;

import ru.itis.springdemo.models.FileInfo;

public interface FilesRepo {
    void save(FileInfo entity);
}
