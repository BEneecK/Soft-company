//package by.bsuir.softcompony.service.email;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SendEmail {
//
//    @Autowired
//    private MailSender mailSender;
//
//    public void sendMail() {
//        SimpleMailMessage msg = new SimpleMailMessage();
//
//        msg.setTo("ivannekrashevich@gmail.com");
//        msg.setFrom("ivannekrashevich@gmail.com");
//        msg.setSubject("Отклик на вакансию");
//        msg.setText("Привет");
//
//        mailSender.send(msg);
//    }
//}
