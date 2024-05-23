package com.ubuntucontinues.ubuntu.util;

public class AppUtils {


    public static final String REQUEST_MESSAGE = "FRIEND_REQUEST";
    public static final String NEW_COHORT_MESSAGE = "Cohort Registered Successfully";
    public static final String COHORT_DOESNT_EXIST = "Cohort With Submitted Credentials Doesn't Exist";
    public static final String COHORT_ALREADY_EXIST = "Cohort With Submitted Credentials Already Exist";
    public static final String NO_COHORT_AVAILABLE = "No Cohort Available";
    public static final String NEW_EVENT_MESSAGE = "Event Created Successfully";
    public static final String EVENT_ALREADY_EXIST = "Event Already Existed";
    public static final String EVENT_DOESNT_EXIST = "Event Doesn't Exist";
    public static final String NO_EVENT_AVAILABLE = "No Event Available";

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
