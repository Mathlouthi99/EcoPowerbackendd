package pi.arctic.ecopower.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
    @Configuration
    public class MailConfig {

        @Bean
        public JavaMailSender javaMailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(465); // or the appropriate port
            mailSender.setUsername("Yassine.mathlouthi@esprit.tn");
            mailSender.setPassword("znnosfiprsncstfp");

            return mailSender;
        }

    }

