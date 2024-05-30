package com.ubuntucontinues.ubuntu.services;


import com.ubuntucontinues.ubuntu.dto.requests.FindAllMessagesRequest;
import com.ubuntucontinues.ubuntu.dto.requests.FindAllMessagesRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SendMessageRequest;
import com.ubuntucontinues.ubuntu.dto.responses.SendMessageResponse;
import com.ubuntucontinues.ubuntu.dto.responses.RecentChats;

import java.util.List;

public interface ChatMessageService {

    SendMessageResponse saveMessage(SendMessageRequest sendMessageRequest);

    List<SendMessageResponse> findAllMessagesBtwSendAndRecipient(FindAllMessagesRequest findAllMessagesRequest);

    List<RecentChats> findRecentlyChats(String email);
}
