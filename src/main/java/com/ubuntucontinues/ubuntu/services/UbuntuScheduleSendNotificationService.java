package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.data.repositories.UserRepository;
import com.ubuntucontinues.ubuntu.dto.requests.Recipient;
import com.ubuntucontinues.ubuntu.dto.requests.Sender;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ubuntucontinues.ubuntu.util.AppUtils.*;

@Service
@AllArgsConstructor
public class UbuntuScheduleSendNotificationService implements ScheduleSendNotificationService {
    private UserService userService;
    private EmailService emailService;
    private PasswordGeneratorServices passwordGeneratorServices;
    private ModelMapper modelMapper;

//    @Scheduled(cron = "* */30 * * * *")
    public void scheduleTask() {
        List<Recipient> currentRecipient = new ArrayList<>();

        for (Recipient recipient : getAllUnActivatedUser()) {
            currentRecipient.add(recipient);
            emailService.sendMessage(new Sender("delighteddeborah5@gmail.com", EMAIL_NAME),
                    LOGIN_MESSAGE(recipient.getPassword()),
                    currentRecipient,
                    LOGIN_SUBJECT
            );
            currentRecipient.clear();
        }

    }

    private void setUserPassword(List<User> users) {
        users.forEach(user -> userService.setLoginPassword(user, passwordGeneratorServices.getPassword()));
    }

    private List<Recipient> getAllUnActivatedUser() {
        List<User> unactivatedUser = userService.getAllUnActivated();
        setUserPassword(unactivatedUser);
        return unactivatedUser
                .stream()
                .map(user -> modelMapper.map(user, Recipient.class))
                .toList();
    }
}
