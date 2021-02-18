package ru.itis.springdemo.repositories.files;

import ru.itis.springdemo.models.FileInfo;
import ru.itis.springdemo.repositories.CrudRepository;

import java.util.Optional;

public interface FilesRepository extends CrudRepository<FileInfo> {
    Optional<FileInfo> findById(Integer id);
}
