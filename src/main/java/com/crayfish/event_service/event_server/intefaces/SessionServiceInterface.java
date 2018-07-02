package com.crayfish.event_service.event_server.intefaces;

import com.crayfish.event_service.event_server.entities.SessionsEntity;

import java.util.List;

public interface SessionServiceInterface {

    List<SessionsEntity> getAll();

    SessionsEntity getById(int id);

    List<SessionsEntity> getSessionsBySpeakerId(int id);

    void updateSessionOrCreateNew(Integer id, String updatingType, String value);

    Integer getLastIdNumber();

    void save(SessionsEntity sessionsEntity);
}
