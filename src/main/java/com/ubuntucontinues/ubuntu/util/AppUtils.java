package com.ubuntucontinues.ubuntu.util;

import java.time.LocalDate;

public class AppUtils {


    public static final String REQUEST_MESSAGE = "FRIEND_REQUEST";
    public static final String NEW_COHORT_MESSAGE = "Cohort Registered Successfully";
    public static final String COHORT_DOESNT_EXIST = "Cohort With Submitted Credentials Doesn't Exist";
    public static final String COHORT_ALREADY_EXIST = "Cohort With Submitted Credentials Already Exist";
    public static final String NO_COHORT_AVAILABLE = "\"\"err\"No Cohort Available\"";
    public static final String NEW_EVENT_MESSAGE = "Event Created Successfully";
    public static final String EVENT_ALREADY_EXIST = "Event Already Existed";
    public static final String EVENT_DOESNT_EXIST = "Event Doesn't Exist";
    public static final String NO_EVENT_AVAILABLE = "No Event Available";
    public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String CHARACTER = "!@#$%^&*";
    public static final String DROP_DOWN_MESSAGE = "User with following id %s does not exist";
    public static final String DELETE_EVENT_MESSAGE = "Event has been deleted";
    public static final String USER_EXIST = "\"\"err\"User Already Exist\"";
    public static final String QUESTION_UPLOADED_MESSAGE = "Question Uploaded Successfully";
    public static final String QUESTION_NOT_EXIST = "Question does not Exist";
    public static final String INITIALIZE_REQUEST_MESSAGE = "Request has been sent to the sender to activate chat";
    public static final String INVALID_DETAIL = "Invalid details";
    public static final String LOGIN_SUBJECT = "LOGIN DETAILS";

    public static final String LOGIN_LINK = "http://localhost:3000/login";
    public static final String EMAIL_NAME = "UBUNTU CONTINUES";
    public static final String CREATE_POST_RESPONSE = "Post Created successfully";
    public static final String DELETE_POST_MESSAGE = "Post has been deleted";
    public static final String POST_NOT_EXIST = "Post does not exist";
    public static final String UPDATE_POST_RESPONSE = "Post updated Successfully";
    public static final String COMMENT_SAVED_RESPONSE = "Comment Added Successfully";
    public static final String LIKED_POST_RESPONSE = "Post has been liked";
    public static final String MEMBERS_ADDED_SUCCESSFULLY = "Members has been added Successfully";
    public static final String CHATROOM_CREATED_MESSAGE = "Chat room created Successfully";
    public static final String ACTIVATE_CHAT = "Chat room has been activated";
    public static final String COMMUNITY_MANAGER = "Community Manager";
    public static final String ADD_STUDENT = "ADD STUDENT MAIL MESSAGE";
    public static final String APP_EMAIL = "delighteddeborah5@gmail.com";
    public static final String COMMENT_NOT_FOUND = "Comment does not exist";
    public static final String DELETE_QUESTION_RESPONSE = "Question Deleted Successfully";


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

    public static String ADD_STUDENT_MESSAGE(String members, int numberOfStudent, int unsavedNumberOfStudent) {
        return String.format("""
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Urgent: Issue with Adding Students to the Application</title>
                </head>
                <body>
                    <p>Dear %s</p>
                    
                    
                    
                    <p>
                        The system encountered an issue while adding students to the Application. Unfortunately, it appears that not all student records were saved successfully. Below are the details:
                    </p>
                   
                    <ul>
                        <li><strong>Date/Time of Attempt:</strong> %s</li>
                        <li><strong>Number of Students Added:</strong> %s</li>
                        <li><strong>Students Not Saved:</strong>%s</li>
                        <li><strong>Students Not Saved:</strong>%s</li>
                    </ul>
                  
                    <p>
                        The system already verified the data. It seems that there may be an issue due to that user already exist.
                    </p>
                   
                    <p>
                        Could you please look into this matter at your earliest convenience?
                    </p>
                   
                    <p>
                        Thank you for your prompt attention to this matter. Looking forward to your guidance on the next steps to resolve this issue.
                    </p>
                   
                    <p>Best regards,</p>
                    <p>Ubuntu Continue<br>
                       Founder<br>
                       312 Hebert Maculay Surulere</p>
                </body>
                </html>
                """, COMMUNITY_MANAGER, LocalDate.now(), numberOfStudent, unsavedNumberOfStudent, members);
    }
}
