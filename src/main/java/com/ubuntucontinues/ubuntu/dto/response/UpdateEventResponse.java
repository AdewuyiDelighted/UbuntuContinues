package com.ubuntucontinues.ubuntu.dto.response;

import com.ubuntucontinues.ubuntu.data.models.Event;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateEventResponse {
    private Event event;
}
