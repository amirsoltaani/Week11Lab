package services;

import dataaccess.UserDB;

import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.User;

import javax.mail.MessagingException;
import javax.naming.NamingException;

public class AccountService {

    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                // Log activity
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);

                // Send E-mail

                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());

                GmailService.sendMail(to, subject, template, tags);


                return user;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean forgotPassword(String email, String path) {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        String subject = "Notes App Reset Password";

        if (user != null) {
            String to = user.getEmail();
            String template = path + "/emailtemplates/forgot.html";
            HashMap<String, String> tags = new HashMap<>();
            tags.put("firstname", user.getFirstName());
            tags.put("lastname", user.getLastName());
            tags.put("username", user.getEmail());
            tags.put("password", user.getPassword());

            try {
                GmailService.sendMail(to, subject, template, tags);
                return true;
            } catch (Exception e) {
                Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, e);
                return false;
            }
        } else {
            return false;
        }
    }
}
