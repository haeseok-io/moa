package kr.it.moa.backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    // 이메일 발송 로직
    public void sendLoginMail(String to, String loginUrl) {
        // html 가공
        Context context = new Context();
        context.setVariable("loginUrl", loginUrl);
        String html = templateEngine.process("email/login", context);

        try {
            // 메일내용
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("모아 이메일 로그인");
            helper.setText(html, true);

            // cid 이미지
            ClassPathResource logo = new ClassPathResource("email/images/moa-logo.png");
            helper.addInline("moa-logo", logo);

            // 메일 발송
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("이메일 발송에 실패하였습니다.", e);
        }
    }

    // 이메일 발송 테스트
    public void sendTestMail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("MOA 테스트 메일");
        message.setText("MOA 이메일 발송 테스트입니다.");

        mailSender.send(message);
    }
}
