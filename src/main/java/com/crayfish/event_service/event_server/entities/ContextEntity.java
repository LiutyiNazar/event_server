package com.crayfish.event_service.event_server.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "context", schema = "events")
public class ContextEntity {

    @Id
    @Column(name = "recipient_id")
    private String recipientId;

    @Basic
    @Column(name = "context_state")
    private String contextState;

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getContextState() {
        return contextState;
    }

    public void setContextState(String contextState) {
        this.contextState = contextState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContextEntity)) return false;
        ContextEntity that = (ContextEntity) o;
        return Objects.equals(recipientId, that.recipientId) &&
                Objects.equals(contextState, that.contextState);
    }

    @Override
    public int hashCode() {

        return Objects.hash(recipientId, contextState);
    }
}
