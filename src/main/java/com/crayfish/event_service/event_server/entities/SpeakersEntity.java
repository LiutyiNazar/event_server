package com.crayfish.event_service.event_server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "speakers", schema = "events", catalog = "")
public class SpeakersEntity {
    private int id;
    private String imageUrl;
    private String firstName;
    private String lastName;
    private String description;
    private String email;
    private List<SessionsEntity> sessions;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        SpeakersEntity that = (SpeakersEntity) o;
        return id == that.id &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, imageUrl, firstName, lastName, description);
    }


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "sessions_and_speakers",
            joinColumns = {
                    @JoinColumn(name = "speaker_id")
            }, inverseJoinColumns = {
            @JoinColumn(name = "session_id")
    })
    @JsonIgnore
    public List<SessionsEntity> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionsEntity> sessions) {
        this.sessions = sessions;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return " Id = " + id +
                " name = " + firstName + "" + lastName +
                " url = " + imageUrl +
                " description = " + description;
    }
}
