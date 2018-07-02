package com.crayfish.event_service.event_server.services;

import com.crayfish.event_service.event_server.entities.SessionsEntity;
import com.crayfish.event_service.event_server.entities.SpeakersEntity;
import com.crayfish.event_service.event_server.intefaces.SpeakerServiceInterface;
import com.crayfish.event_service.event_server.repositories.SpeakersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpeakerService implements SpeakerServiceInterface {
    @Autowired
    private SpeakersRepository speakersRepository;

    @Autowired
    private SessionService sessionService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<SpeakersEntity> getAll() {
        return speakersRepository.findAll();
    }

    public SpeakersEntity getById(int id) {
        return speakersRepository.getOne(id);
    }

    public List<SpeakersEntity> getSpeakersBySessionId(int id) {
        logger.info("Getting speakers by session id");
        return speakersRepository.findTop10BySessions_id(id);

    }

    public void updateSpeakerOrCreateNew(Integer id, String updateType, String value) {
        logger.info("Updating speaker");
        SpeakersEntity entity;
        if (speakersRepository.count() != id) {
            logger.info("creating new, because speaker don`t exist");
            entity = new SpeakersEntity();
        } else {
            entity = getById(id);
        }
        entity.setId(id);
        speakersRepository.saveAndFlush(entity);

        switch (updateType) {
            case "id": {
                break;
            }
            case "firstName": {
                entity.setFirstName(value);
                break;
            }
            case "lastName": {
                entity.setLastName(value);
                break;
            }
            case "imageUrl": {
                entity.setImageUrl(value);
                break;
            }
            case "description": {
                entity.setDescription(value);
                break;
            }
            case "email": {
                entity.setEmail(value);
                break;
            }
            case "sessions": {
                logger.info("Trying to execute adding session to speaker");
                SessionsEntity sessionsEntity = sessionService.getById(Integer.parseInt(value));
                List<SessionsEntity> sessionsEntityList = new ArrayList<>();
                sessionsEntityList.add(sessionsEntity);
                entity.setSessions(sessionsEntityList);
                List<SpeakersEntity> speakersEntities = sessionsEntity.getSpeakers();
                speakersEntities.add(entity);
                sessionsEntity.setSpeakers(speakersEntities);
                speakersRepository.save(entity);
                sessionService.save(sessionsEntity);
                break;
            }
        }


        speakersRepository.saveAndFlush(entity);
    }

    public Integer getLastIdNumber() {
        Integer count = 0;
        for (SpeakersEntity speakersEntity : getAll()) {
            count = speakersEntity.getId();
        }
        return count;
    }

    public void save(SpeakersEntity speakersEntity) {
        speakersRepository.save(speakersEntity);
    }
}
