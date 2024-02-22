package com.OTP.Service.OTP.service.Impl;

import com.OTP.Service.OTP.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
// static import from
import static com.OTP.Service.OTP.service.Impl.EmailVerificationServiceImpl.emailOtpMapping;

@Service
public class EmailService_Impl implements EmailService {
    private final JavaMailSender javaMailSender;

    // Constructor for JavaMailSender injection
    public EmailService_Impl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // Method to send OTP email
    @Override
    public void sendOtpEmail(String email) {
        // Generate OTP and save it for later verification
        String otp = this.generateOtp();
        //
        emailOtpMapping.put(email, otp);

        // Send email with OTP
        this.sendEmail(email, "Your OTP is: " + otp);
    }

    // Method to generate OTP
    public String generateOtp() {
        // Generate a 6-digit OTP with leading zeros
        return String.format("%06d", new java.util.Random().nextInt(1000000));
    }

    // Method to send email
    private void sendEmail(String to, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("arkodey.ad@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("OTP for Email Verification");
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }
}
