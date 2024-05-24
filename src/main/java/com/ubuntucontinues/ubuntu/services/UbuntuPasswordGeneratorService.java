package com.ubuntucontinues.ubuntu.services;

import org.springframework.stereotype.Service;


import java.util.Random;

import static com.ubuntucontinues.ubuntu.util.AppUtils.*;

@Service
public class UbuntuPasswordGeneratorService implements PasswordGeneratorServices {

    Random randomNumber = new Random();


    @Override
    public String getPassword() {

        String password = "";
        int uppercaseIndex;
        int lowercaseIndex;
        int number;

        int characterIndex = randomNumber.nextInt(0, 7);
        for (int index = 0; index < 3; index++) {
            uppercaseIndex = randomNumber.nextInt(0, 25);
            lowercaseIndex = randomNumber.nextInt(0, 25);
            number = randomNumber.nextInt(1, 9);

            password += UPPERCASE.charAt(uppercaseIndex);
            password += LOWERCASE.charAt(lowercaseIndex);
            password += number;
        }
        password += CHARACTER.charAt(characterIndex);
        return shuffleString(password);
    }


    private String shuffleString(String password) {
        int swapIndex;
        StringBuilder shuffledPassword = new StringBuilder(password);
        for (int index = 0; index < 3; index++) {
            swapIndex = randomNumber.nextInt(0, password.length());

            while (swapIndex == index) {
                swapIndex = randomNumber.nextInt(0, password.length());
            }

            char letterAtIndex = password.charAt(index);
            char letterAtSwapIndex = password.charAt(swapIndex);

            shuffledPassword.setCharAt(index, letterAtSwapIndex);
            shuffledPassword.setCharAt(swapIndex, letterAtIndex);
        }
        return shuffledPassword.toString();
    }
}
