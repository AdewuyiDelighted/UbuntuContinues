package com.ubuntucontinues.ubuntu.util;

public class AppUtils {

    public static final String REQUEST_MESSAGE = "FRIEND_REQUEST";
    public static final String DROP_DOWN_MESSAGE = "User with following id %s does not exist";


    public static String INITIATE_REQUEST_MESSAGE(String senderEmail, String recipientEmail, String link) {
        return String.format("""
                <!DOCTYPE>
                <head>
                <title>Invitation Request</title>
                </head>
                <body>
                <h1>Friend Request</h1>
                <h4>Hello %s</h4>
                <p>You Have Received a chat request from %s Please respond accordingly by Accepting Request</p>
                <a href="%s" target="_blank"><button style="color":blue;>Accept</button></a>
                </body>
                """, recipientEmail, senderEmail, link);
    }
}
