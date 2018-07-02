package com.crayfish.event_service.event_server.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessions", schema = "events")
public class SessionsEntity {
    private int id;
    private String name;
    private String time;
    private String description;
    private List<SpeakersEntity> speakers;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionsEntity that = (SessionsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(time, that.time) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, time, description);
    }
// CascadeType.PERSIST,
//            CascadeType.MERGE
    @ManyToMany( fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "sessions")
    public List<SpeakersEntity> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<SpeakersEntity> speakers) {
        this.speakers = speakers;
    }

    @Override
    public String toString() {
        return " Id = " + id +
                " name = " + name+
                " time = "+ time+
                " description = " + description;
    }
}
