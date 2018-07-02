package com.crayfish.event_service.event_server.repositories;

import com.crayfish.event_service.event_server.entities.SessionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<SessionsEntity, Integer> {

    List<SessionsEntity> findTop10BySpeakers_id(Integer speakerId);

}
