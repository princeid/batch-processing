package com.prince.jobmanagementsystem.jobs;

import com.prince.jobmanagementsystem.domain.Job;
import com.prince.jobmanagementsystem.domain.Priority;
import com.prince.jobmanagementsystem.domain.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSendingJob extends Job {
    Logger log = LoggerFactory.getLogger(EmailSendingJob.class);

    public EmailSendingJob(Priority priority) {
        super(priority);
    }

    @Override
    public void run() {
        setState(State.RUNNING);
        log.info("EMAIL SENDING JOB STATE IN RUN METHOD: {}", getState());
        log.info("Email Sending Job is Running...");
        sendMail("testemail@examplemail.test");
    }

    public void sendMail(String recipient) {
        log.info("Sending email...");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        /* Set your sender's email address and password in the environment variable to test this service. */
        String myGmailAccount = System.getenv("MY_GMAIL");
        String myGmailPassword = System.getenv("PASSWORD");

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myGmailAccount, myGmailPassword);
            }
        });

        Message message = prepareMessage(session, myGmailAccount, recipient);

        if (message != null) {
            try {
                Transport.send(message);
                setState(State.SUCCESS);
                log.info("Email sent successfully.");
            } catch (MessagingException e) {
                setState(State.FAILED);
                log.info("Email failed to send.");
                e.printStackTrace();
            }
        }

    }

    private static Message prepareMessage(Session session, String myEmail, String recipient) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("J2SE Case Study - Job Management Service");
            message.setText("Email sending job to verify the fulfillment of requirements.");
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
