package com.crayfish.event_service.event_server.intefaces;

import com.crayfish.event_service.event_server.entities.ContextEntity;

import java.util.List;

public interface ContextServiceInterface {

    List<ContextEntity> getAll();

    ContextEntity getByRecipientId(String id);

    ContextEntity updateContextState(String recipientId, String value);
}
