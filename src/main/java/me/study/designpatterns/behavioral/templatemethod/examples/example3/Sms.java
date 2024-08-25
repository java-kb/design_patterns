package me.study.designpatterns.behavioral.templatemethod.examples.example3;

import java.util.Random;

class Sms implements IOtp {
    @Override
    public String genRandomOTP(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        System.out.println("SMS: generating random otp " + sb.toString());
        return sb.toString();
    }

    @Override
    public void saveOTPCache(String otp) {
        System.out.println("SMS: saving otp: " + otp + " to cache");
    }

    @Override
    public String getMessage(String otp) {
        return "SMS OTP for login is " + otp;
    }

    @Override
    public void sendNotification(String message) throws Exception {
        System.out.println("SMS: sending sms: " + message);
    }
}