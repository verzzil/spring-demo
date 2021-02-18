package ru.itis.springdemo.services.files;

import ru.itis.springdemo.models.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

public interface FilesService {
    void saveFileToStorage(
            InputStream stream,
            Integer userId,
            String originalFileName,
            String contentType
    );
    void writeFileFromStorage(Integer userId, OutputStream stream);
    Optional<FileInfo> getFileInfo(Integer userId);
}
