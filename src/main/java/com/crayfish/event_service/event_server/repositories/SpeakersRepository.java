package com.crayfish.event_service.event_server.repositories;

import com.crayfish.event_service.event_server.entities.SpeakersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeakersRepository extends JpaRepository<SpeakersEntity, Integer> {
    List<SpeakersEntity> findTop10BySessions_id(Integer id);
}
