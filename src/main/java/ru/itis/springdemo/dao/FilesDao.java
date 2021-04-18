package ru.itis.springdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springdemo.models.FileInfo;

import java.util.Optional;

public interface FilesDao extends JpaRepository<FileInfo, Integer> {
    Optional<FileInfo> findByStorageFileNameByUserId(String fileName);
}
