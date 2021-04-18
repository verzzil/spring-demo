package ru.itis.springdemo.services.files;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.springdemo.models.FileInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

public interface FilesService {
    String saveFile(MultipartFile file, Integer userId);

    void writeFileToResponse(String fileName, HttpServletResponse response);
}
