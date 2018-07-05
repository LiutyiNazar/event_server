package com.crayfish.eventservice.event.server.services;

import com.crayfish.eventservice.event.server.entities.SessionsEntity;
import com.crayfish.eventservice.event.server.entities.SpeakersEntity;
import com.crayfish.eventservice.event.server.intefaces.SessionServiceInterface;
import com.crayfish.eventservice.event.server.repositories.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService implements SessionServiceInterface {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SpeakerService speakerService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<SessionsEntity> getAll() {
        return sessionRepository.findAll();
    }

    @Override
    public SessionsEntity getById(int id) {
        return sessionRepository.getOne(id);
    }

    @Override
    public List<SessionsEntity> getSessionsBySpeakerId(int id) {
        logger.info("Getting session by speaker id");
        return sessionRepository.findTop10BySpeakers_id(id);
    }

    @Override
    public void updateSessionOrCreateNew(Integer id, String updatingType, String value) {
        logger.info("Updating session");
        SessionsEntity entity;
        if (getLastIdNumber() != id) {
            logger.info("Creating new session. because requested don`t exist");
            entity = new SessionsEntity();
        } else {
            entity = getById(id);
        }

        //entity.setId(id);
        switch (updatingType) {
            case "id": {
                break;
            }
            case "name": {
                entity.setName(value);
                break;
            }
            case "time": {
                entity.setTime(value);
                break;
            }
            case "description": {
                entity.setDescription(value);
                break;
            }
            case "speakers": {
                logger.info("Trying to execute adding speaker to session");

                SpeakersEntity speakersEntity = speakerService.getById(Integer.parseInt(value));
                List<SpeakersEntity> speakersEntityList = new ArrayList<>();
                speakersEntityList.add(speakersEntity);
                entity.setSpeakers(speakersEntityList);
                List<SessionsEntity> sessionsEntities = speakersEntity.getSessions();
                sessionsEntities.add(entity);
                speakersEntity.setSessions(sessionsEntities);

                sessionRepository.save(entity);
                speakerService.save(speakersEntity);
                break;
            }
        }


        sessionRepository.saveAndFlush(entity);
    }

    @Override
    public Integer getLastIdNumber() {
        Integer count = 0;
        for (SessionsEntity sessionsEntity : getAll()) {
            count = sessionsEntity.getId();
        }
        return count;
    }

    @Override
    public void save(SessionsEntity sessionsEntity) {
        sessionRepository.save(sessionsEntity);
    }


}
