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
    public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String CHARACTER = "!@#$%^&*";
    public static final String DROP_DOWN_MESSAGE = "User with following id %s does not exist";
    public static final String DELETE_EVENT_MESSAGE = "Event has been deleted";
    public static final String USER_NOT_EXIST = "User does not Exist";
    public static final String QUESTION_UPLOADED_MESSAGE = "Question Uploaded Successfully";
    public static final String QUESTION_NOT_EXIST = "Question does not Exist";
    public static final String INITIALIZE_REQUEST_MESSAGE = "Request has been sent to the sender to activate chat";
    public static final String INVALID_DETAIL = "Invalid details";
    public static final String LOGIN_SUBJECT = "LOGIN DETAILS";

    public static final String LOGIN_LINK = "http://localhost:3000/login";
    public static final String EMAIL_NAME = "UBUNTU CONTINUES";


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

    public static String NOTIFICATION_MESSAGE(String title, String message) {
        return String.format("""
                <!DOCTYPE>
                <head>
                <title>%s</title>
                </head>
                <body>
                <p>%s</>
                </body>
                """, title, message);
    }

    public static String LOGIN_MESSAGE(String password) {
        return String.format("""
                <!DOCTYPE>
                <head>
                <title>Login Details</title>
                </head>
                <body>
                <p>
                We are excited to welcome you to [Your Community Chat App]! To get started, please log in using the link and password provided below.
`              <b>Login Details:</b>
                Login Link:%s
                Password:%s
                
                Thank you for joining our community!
                <p/>
                </body>
                    """, LOGIN_LINK,password);

    }
}
