package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.ChatMessage;
import com.ubuntucontinues.ubuntu.dto.request.FindAllMessagesRequest;
import com.ubuntucontinues.ubuntu.dto.request.SendMessageRequest;
import com.ubuntucontinues.ubuntu.dto.response.SendMessageResponse;

import java.util.List;

public interface ChatMessageService {

    SendMessageResponse saveMessage(SendMessageRequest sendMessageRequest);

    List<SendMessageResponse> findAllMessagesBtwSendAndRecipient(FindAllMessagesRequest findAllMessagesRequest);
}
