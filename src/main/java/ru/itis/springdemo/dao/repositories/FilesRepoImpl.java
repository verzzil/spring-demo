package ru.itis.springdemo.dao.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.itis.springdemo.models.FileInfo;

import java.util.Optional;

@Component
public class FilesRepoImpl implements FilesRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private final static String SQL_INSERT = "insert into file_info(storage_file_name_by_user_id, original_file_name, type) values (?, ?, ?)";

    //language=SQL
    private final static String SQL_UPDATE = "update file_info set original_file_name = ?, type = ? where storage_file_name_by_user_id = ?";

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "select * from file_info where storage_file_name_by_user_id = ?";

    @Override
    public void save(FileInfo entity) {
        Optional<Boolean> isExistRow = Optional.ofNullable(jdbcTemplate.query(
                SQL_SELECT_BY_ID,
                new Object[]{entity.getStorageFileNameByUserId()},
                resultSet -> {
                    return resultSet.next();
                }
        ));

        if (isExistRow.isPresent()) {
            if (isExistRow.get())
                jdbcTemplate.update(
                        SQL_UPDATE,
                        entity.getOriginalFileName(),
                        entity.getType(),
                        entity.getStorageFileNameByUserId()
                );
            else
                jdbcTemplate.update(
                        SQL_INSERT,
                        entity.getStorageFileNameByUserId(),
                        entity.getOriginalFileName(),
                        entity.getType()
                );
        }
    }
}
