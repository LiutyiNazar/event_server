package com.crayfish.eventservice.event.server.services;

import com.crayfish.eventservice.event.server.entities.ContextEntity;
import com.crayfish.eventservice.event.server.intefaces.ContextServiceInterface;
import com.crayfish.eventservice.event.server.repositories.ContextRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContextService implements ContextServiceInterface {
    @Autowired
    private ContextRepository contextRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<ContextEntity> getAll() {
        return contextRepository.findAll();
    }

    @Override
    public ContextEntity getByRecipientId(String id) {

        return contextRepository.findFirstByRecipientId(id);

    }

    @Override
    public ContextEntity updateContextState(String recipientId, String value) {
        logger.info("updating context state");
        ContextEntity entity = new ContextEntity();
        entity.setContextState(value);
        entity.setRecipientId(recipientId);
        return contextRepository.saveAndFlush(entity);
    }


}
