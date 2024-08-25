package me.study.designpatterns.behavioral.templatemethod.examples.example3;

public class TestApp {
    public static void main(String[] args) {
        Sms smsOTP = new Sms();
        OtpClient o = new OtpClient(smsOTP);
        o.genAndSendOTP(4);

        System.out.println();

        Email emailOTP = new Email();
        o = new OtpClient(emailOTP);
        o.genAndSendOTP(4);
    }
}