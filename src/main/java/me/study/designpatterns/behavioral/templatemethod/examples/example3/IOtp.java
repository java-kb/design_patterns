package me.study.designpatterns.behavioral.templatemethod.examples.example3;

interface IOtp {
    String genRandomOTP(int length);
    void saveOTPCache(String otp);
    String getMessage(String otp);
    void sendNotification(String message) throws Exception;
}


