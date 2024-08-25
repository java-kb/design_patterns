package me.study.designpatterns.behavioral.templatemethod.examples.example3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OtpClientTest {
    @Test
    public void testGenAndSendOTPUsingSms() {
        Sms smsOTP = new Sms();
        OtpClient o = new OtpClient(smsOTP);
        o.genAndSendOTP(4);
    }
    
    @Test
    public void testGenAndSendOTPUsingEmail() {
        Sms smsOTP = new Sms();
        OtpClient o = new OtpClient(smsOTP);
        o.genAndSendOTP(4);
    }
}
