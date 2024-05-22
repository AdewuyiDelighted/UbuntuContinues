package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.response.UpdateEventResponse;

public interface EventService {

   UpdateEventResponse updateEvent(UpdateEventRequest request) ;

}
