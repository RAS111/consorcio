package com.c823.consorcio.repository;

import com.c823.consorcio.entity.MessageEntity;
import com.c823.consorcio.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageRepository extends JpaRepository<MessageEntity, Long> {

  List<MessageEntity> findAllByUser(UserEntity user);
}
