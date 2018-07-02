package com.crayfish.event_service.event_server.intefaces;

import com.crayfish.event_service.event_server.entities.SpeakersEntity;

import java.util.List;

public interface SpeakerServiceInterface {

    List<SpeakersEntity> getAll();

    SpeakersEntity getById(int id);

    List<SpeakersEntity> getSpeakersBySessionId(int id);

    void updateSpeakerOrCreateNew(Integer id, String updateType, String value);

    Integer getLastIdNumber();

    void save(SpeakersEntity speakersEntity);
}
