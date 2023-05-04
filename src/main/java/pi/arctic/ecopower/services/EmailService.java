package pi.arctic.ecopower.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pi.arctic.ecopower.DTO.Payment;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService  implements  IEmailService{
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendPaymentReceiptEmail(String recipientEmail, Payment payment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Payment Receipt");
        message.setText("Thank you for your payment.\n\nAmount: " + payment.getAmount()
                + "\nCurrency: " + payment.getCurrency() + "\n\nRegards,\nYour App");

        javaMailSender.send(message);
    }
    public void sendEmail(String recipient, String subject, String message) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");


            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom("yassine.mathlouthi@esprit.tn");
            email.setTo(recipient);
            email.setSubject(subject);
            email.setText(message);

        javaMailSender.send(email);

    }


    public void sendInstallationConfirmation(String to, Date installationDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Installation Confirmation");
        message.setText("Your installation has been confirmed for " + installationDate.toString() + ".");
        javaMailSender.send(message);
    }


}
