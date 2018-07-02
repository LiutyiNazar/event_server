package com.crayfish.event_service.event_server.Domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Furios on 12.06.2018.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpeakerUpdate {
    private Integer id;
    private String updateType;
    private String value;
}
