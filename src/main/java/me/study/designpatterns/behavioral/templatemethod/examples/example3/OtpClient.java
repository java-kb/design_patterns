package me.study.designpatterns.behavioral.templatemethod.examples.example3;

class OtpClient {
    private IOtp iOtp;

    public OtpClient(IOtp iOtp) {
        this.iOtp = iOtp;
    }

    public void genAndSendOTP(int otpLength) {
        String otp = iOtp.genRandomOTP(otpLength);
        iOtp.saveOTPCache(otp);
        String message = iOtp.getMessage(otp);
        try {
            iOtp.sendNotification(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}