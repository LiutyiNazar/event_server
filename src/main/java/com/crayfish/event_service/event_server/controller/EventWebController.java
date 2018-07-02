package com.crayfish.event_service.event_server.controller;

import com.crayfish.event_service.event_server.Domains.ContextUpdate;
import com.crayfish.event_service.event_server.Domains.SessionUpdate;
import com.crayfish.event_service.event_server.Domains.SpeakerUpdate;
import com.crayfish.event_service.event_server.entities.ContextEntity;
import com.crayfish.event_service.event_server.entities.SessionsEntity;
import com.crayfish.event_service.event_server.entities.SpeakersEntity;
import com.crayfish.event_service.event_server.services.ContextService;
import com.crayfish.event_service.event_server.services.SessionService;
import com.crayfish.event_service.event_server.services.SpeakerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventWebController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SpeakerService speakerService;
    @Autowired
    private ContextService contextService;


    @GetMapping("/sessions")
    public @ResponseBody
    SessionsEntity[] getSessions() {
        List<SessionsEntity> entities = sessionService.getAll();
        SessionsEntity[] sessionsEntities = new SessionsEntity[entities.size()];
        entities.toArray(sessionsEntities);
        return sessionsEntities;
    }

    @GetMapping("/speakers")
    public @ResponseBody
    SpeakersEntity[] getSpeakers() {
        List<SpeakersEntity> entities = speakerService.getAll();
        SpeakersEntity[] speakersEntities = new SpeakersEntity[entities.size()];
        entities.toArray(speakersEntities);
        return speakersEntities;
    }

    @GetMapping("/speakersBySessionId")
    public @ResponseBody
    SpeakersEntity[] getSpeakersBySessionId(@RequestParam int id) {
        logger.info("Received command to getSpeakers by Session id");
        List<SpeakersEntity> speakersEntities = speakerService.getSpeakersBySessionId(id);
        SpeakersEntity[] speakersEntitiesArray = new SpeakersEntity[speakersEntities.size()];
        speakersEntities.toArray(speakersEntitiesArray);
        logger.info("Command has been successfully executed");
        return speakersEntitiesArray;
    }

    @GetMapping("/sessionsBySpeakerId")
    public @ResponseBody
    SessionsEntity[] getSessionsBySpeakerId(@RequestParam int id) {
        logger.info("Received command to getSessions By Speaker Id");
        List<SessionsEntity> sessionsEntities = sessionService.getSessionsBySpeakerId(id);
        SessionsEntity[] entities = new SessionsEntity[sessionsEntities.size()];
        sessionsEntities.toArray(entities);
        logger.info("Command has been successfully executed");
        return entities;
    }

    @GetMapping("/context")
    public @ResponseBody
    ContextEntity[] getContext() {
        logger.info("Received command to get all Context");
        List<ContextEntity> contexts = contextService.getAll();
        ContextEntity[] contextEntities = new ContextEntity[contexts.size()];
        contexts.toArray(contextEntities);
        return contextEntities;
    }

    @GetMapping("/contextByRecipient")
    public @ResponseBody
    ContextEntity getContextByRecipientId(@RequestParam String id) {
        logger.info("Received command to get context by recepient id");
        ContextEntity contextEntity;
        contextEntity = contextService.getByRecipientId(id);
        if (contextEntity == null) {
            contextEntity = contextService.updateContextState(id, "Ended");
        }

        return contextEntity;
    }

    @GetMapping("/sessionsCount")
    public @ResponseBody
    Integer getCountOfSessions() {
        return sessionService.getLastIdNumber();
    }

    @GetMapping("/speakersCount")
    public @ResponseBody
    Integer getCountOfSpeakers() {
        return speakerService.getLastIdNumber();
    }


    @PutMapping("/sessionUpdate")
    public void doUpdateForSession(@RequestBody SessionUpdate sessionUpdate){
        Integer id = sessionUpdate.getId();
        String updateType = sessionUpdate.getUpdateType();
        String value = sessionUpdate.getValue();
        sessionService.updateSessionOrCreateNew(id, updateType,value);
    }

    @PutMapping("/speakerUpdate")
    public void doUpdateForSpeaker(@RequestBody SpeakerUpdate speakerUpdate){
        Integer id = speakerUpdate.getId();
        String updateType = speakerUpdate.getUpdateType();
        String value = speakerUpdate.getValue();
        speakerService.updateSpeakerOrCreateNew(id, updateType,value);
    }

    @PutMapping("/contextUpdate")
    public void doUpdateForContext(@RequestBody ContextUpdate contextUpdate){
        String id = contextUpdate.getId();
        String state = contextUpdate.getState();
        contextService.updateContextState(id, state);
    }

}
