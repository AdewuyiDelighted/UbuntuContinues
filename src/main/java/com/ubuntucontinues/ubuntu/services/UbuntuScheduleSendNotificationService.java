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

import java.util.List;

import static com.ubuntucontinues.ubuntu.util.AppUtils.*;

@Service
@AllArgsConstructor
public class UbuntuScheduleSendNotificationService implements ScheduleSendNotificationService {
    private UserService userService;
    private EmailService emailService;
    private PasswordGeneratorServices passwordGeneratorServices;
    private ModelMapper modelMapper;
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

    @Scheduled(cron = "*/2 * * * * *")
    public void scheduleTask() {
//        setUserPassword(userService.getAllUnActivated());
//        System.out.println(userService.getAllUnActivated());

//        emailService.sendMessage(new Sender("delighteddeborah5@gmail.com", EMAIL_NAME),
//                LOGIN_MESSAGE(passwordGeneratorServices.getPassword()),
//                getUnActivatedUser(),
//                LOGIN_SUBJECT
//        );

    }

    private void setUserPassword(List<User> users) {
        users.forEach(user -> userService.setLoginPassword(user, passwordGeneratorServices.getPassword()));
    }

    private List<Recipient> getAllUnActivatedUser() {
        List<User> unActivatedUser = userService.getAllUnActivated();

        setUserPassword(unActivatedUser);
        return null;

    }
}
