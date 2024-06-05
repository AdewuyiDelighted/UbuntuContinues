package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.dto.requests.Recipient;
import com.ubuntucontinues.ubuntu.dto.requests.Sender;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Scheduled(cron = "* 51 0 * * *")
    public void scheduleTask() {
        System.out.println("1");
//        List<Recipient> recipients = getAllUnActivatedUser();
//        System.out.println("2");
//        for (Recipient recipient : recipients) {
//            System.out.println("3");
//            List<Recipient> currentRecipient = new ArrayList<>();
//            currentRecipient.add(recipient);
//            System.out.println("recipient1 " + recipient);
//            emailService.sendMessage(new Sender("delighteddeborah5@gmail.com", EMAIL_NAME),
//                    LOGIN_MESSAGE(recipient.getPassword()),
//                    currentRecipient,
//                    LOGIN_SUBJECT
//            );
//            System.out.println("recipient2 " + recipient);
//        }
    }

    @Override
    public void sendLoginEmail(List<User> users){
        setUserPassword(users);
        List<Recipient> recipients = users.stream().map(user -> {
            try {
                return modelMapper.map(userService.findBy(user.getId()), Recipient.class);
            } catch (UserExistException e) {
                return null;
            }
        }).toList();
        for (Recipient recipient : recipients){
            List<Recipient> currentRecipient = new ArrayList<>();
            currentRecipient.add(recipient);
            emailService.sendMessage(new Sender("delighteddeborah5@gmail.com", EMAIL_NAME),
                    LOGIN_MESSAGE(recipient.getPassword()),
                    currentRecipient,
                    LOGIN_SUBJECT
            );
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
