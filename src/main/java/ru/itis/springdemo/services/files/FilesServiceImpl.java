package ru.itis.springdemo.services.files;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.springdemo.dao.FilesDao;
import ru.itis.springdemo.dao.repositories.FilesRepo;
import ru.itis.springdemo.models.FileInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    private FilesDao filesDao;

    @Autowired
    private FilesRepo filesRepo;

    private String storagePath = "D:\\Projects\\spring-demo\\src\\main\\resources\\static\\images\\usersPhotos";

    @Override
    public String saveFile(MultipartFile uploadFile, Integer userId) {
        FileInfo file = FileInfo.builder()
                .type(uploadFile.getContentType())
                .originalFileName(uploadFile.getOriginalFilename())
                .storageFileNameByUserId(String.valueOf(userId))
                .size(uploadFile.getSize())
                .url(storagePath + "\\" + userId + "." + FilenameUtils.getExtension(uploadFile.getOriginalFilename()))
                .build();

        try {
            Files.deleteIfExists(Paths.get(storagePath, userId + "." + FilenameUtils.getExtension(uploadFile.getOriginalFilename())));
            Files.copy(uploadFile.getInputStream(), Paths.get(storagePath, userId + "." + FilenameUtils.getExtension(uploadFile.getOriginalFilename())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        filesRepo.save(file);
        return file.getUrl();
    }

    @Override
    public void writeFileToResponse(String fileName, HttpServletResponse response) {
        Optional<FileInfo> fileInfo = filesDao.findByStorageFileNameByUserId(fileName);
        if (fileInfo.isPresent()) {
            response.setContentType(fileInfo.get().getType());
            try {
                FileInputStream stream = new FileInputStream(fileInfo.get().getUrl());
                IOUtils.copy(stream, response.getOutputStream());
                stream.close();
                response.flushBuffer();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        } else {
            response.setContentType("image/png");
            try {
                FileInputStream stream = new FileInputStream("D:\\Projects\\spring-demo\\src\\main\\resources\\static\\images\\usersPhotos\\default.png");
                IOUtils.copy(stream, response.getOutputStream());
                stream.close();
                response.flushBuffer();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
