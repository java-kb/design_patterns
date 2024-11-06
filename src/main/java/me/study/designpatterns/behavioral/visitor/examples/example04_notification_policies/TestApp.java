package me.study.designpatterns.behavioral.visitor.examples.example04_notification_policies;

import java.util.ArrayList;
import java.util.List;

public class TestApp {

    public static void main(String[] args) {
        Email email = new Email("some@email.com");
        SMS sms = new SMS("+3806700000");
        Push push = new Push("Spammer");

        List<Notification> notifications = new ArrayList<>();
        notifications.add(new EmailNotification(email));
        notifications.add(new SMSNotification(sms));
        notifications.add(new PushNotification(push));

        clientCode(notifications, new DefaultPolicyVisitor());
        clientCode(notifications, new NightPolicyVisitor());
    }

    private static void clientCode(List<Notification> notifications, NotificationPolicy policy) {
        BlackListVisitor blackList = createBlackList();

        System.out.println("\nClient: Using " + policy + " and " + blackList);

        for (Notification item : notifications) {
            if (item.accept(blackList)) {
                System.out.println("\tWARNING: " + item.toString() + " is in a black list");
                continue;
            }

            if (item.accept(policy)) {
                System.out.println("\t" + item.toString() + " notification will be shown");
            } else {
                System.out.println("\t" + item.toString() + " notification will be silenced");
            }
        }
    }

    private static BlackListVisitor createBlackList() {
        List<String> emails = new ArrayList<>();
        emails.add("banned@email.com");
        List<String> phones = new ArrayList<>();
        phones.add("000000000");
        phones.add("1234325232");
        List<String> usernames = new ArrayList<>();
        usernames.add("Spammer");
        return new BlackListVisitor(emails, phones, usernames);
    }

}

interface Notification {
    boolean accept(NotificationPolicy visitor);
}

class Email {
    private String emailOfSender;

    public Email(String emailOfSender) {
        this.emailOfSender = emailOfSender;
    }

    public String getEmailOfSender() {
        return emailOfSender;
    }

    @Override
    public String toString() {
        return "Email";
    }
}

class SMS {
    private String phoneNumberOfSender;

    public SMS(String phoneNumberOfSender) {
        this.phoneNumberOfSender = phoneNumberOfSender;
    }

    public String getPhoneNumberOfSender() {
        return phoneNumberOfSender;
    }

    @Override
    public String toString() {
        return "SMS";
    }
}

class Push {
    private String usernameOfSender;

    public Push(String usernameOfSender) {
        this.usernameOfSender = usernameOfSender;
    }

    public String getUsernameOfSender() {
        return usernameOfSender;
    }

    @Override
    public String toString() {
        return "Push";
    }
}

class EmailNotification implements Notification {
    private Email email;

    public EmailNotification(Email email) {
        this.email = email;
    }

    @Override
    public boolean accept(NotificationPolicy visitor) {
        return visitor.isTurnedOn(email);
    }

    @Override
    public String toString() {
        return "Email";
    }
}

class SMSNotification implements Notification {
    private SMS sms;

    public SMSNotification(SMS sms) {
        this.sms = sms;
    }

    @Override
    public boolean accept(NotificationPolicy visitor) {
        return visitor.isTurnedOn(sms);
    }

    @Override
    public String toString() {
        return "SMS";
    }
}

class PushNotification implements Notification {
    private Push push;

    public PushNotification(Push push) {
        this.push = push;
    }

    @Override
    public boolean accept(NotificationPolicy visitor) {
        return visitor.isTurnedOn(push);
    }

    @Override
    public String toString() {
        return "Push";
    }
}

interface NotificationPolicy {
    boolean isTurnedOn(Email email);

    boolean isTurnedOn(SMS sms);

    boolean isTurnedOn(Push push);
}

class NightPolicyVisitor implements NotificationPolicy {
    @Override
    public boolean isTurnedOn(Email email) {
        return false;
    }

    @Override
    public boolean isTurnedOn(SMS sms) {
        return true;
    }

    @Override
    public boolean isTurnedOn(Push push) {
        return false;
    }

    @Override
    public String toString() {
        return "Night Policy Visitor";
    }
}

class DefaultPolicyVisitor implements NotificationPolicy {
    @Override
    public boolean isTurnedOn(Email email) {
        return true;
    }

    @Override
    public boolean isTurnedOn(SMS sms) {
        return true;
    }

    @Override
    public boolean isTurnedOn(Push push) {
        return true;
    }

    @Override
    public String toString() {
        return "Default Policy Visitor";
    }
}

class BlackListVisitor implements NotificationPolicy {
    private List<String> bannedEmails;
    private List<String> bannedPhones;
    private List<String> bannedUsernames;

    public BlackListVisitor(List<String> emails, List<String> phones, List<String> usernames) {
        this.bannedEmails = emails;
        this.bannedPhones = phones;
        this.bannedUsernames = usernames;
    }

    @Override
    public boolean isTurnedOn(Email email) {
        return bannedEmails.contains(email.getEmailOfSender());
    }

    @Override
    public boolean isTurnedOn(SMS sms) {
        return bannedPhones.contains(sms.getPhoneNumberOfSender());
    }

    @Override
    public boolean isTurnedOn(Push push) {
        return bannedUsernames.contains(push.getUsernameOfSender());
    }

    @Override
    public String toString() {
        return "Black List Visitor";
    }
}
