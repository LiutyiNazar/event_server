package com.crayfish.event_service.event_server.repositories;

import com.crayfish.event_service.event_server.entities.ContextEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContextRepository extends JpaRepository<ContextEntity, String> {

    ContextEntity findFirstByRecipientId(String recipientId);
}
