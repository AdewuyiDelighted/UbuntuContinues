package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.response.UpdateEventResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UbuntuEventService implements EventService{
    @Override
    public UpdateEventResponse updateEvent(UpdateEventRequest request) {

        return null;
    }
}
