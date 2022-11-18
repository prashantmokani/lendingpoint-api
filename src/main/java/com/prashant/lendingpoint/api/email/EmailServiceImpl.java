package com.prashant.lendingpoint.api.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final Configuration freemarkerConfig;

    public EmailServiceImpl(final JavaMailSender emailSender, final Configuration freemarkerConfig) {
        this.emailSender = emailSender;
        this.freemarkerConfig = freemarkerConfig;
    }

    @Async("asyncExecutor")
    @Override
    public void sendEmail(final Mail mail, final String templateName) {
        log.info("Sending email to --> {} ", mail.getTo().toString());

        try {
            final MimeMessage mimeMessage = emailSender.createMimeMessage();

            final MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            final Template template = freemarkerConfig.getTemplate(templateName);
            final Map<String, Object> model = mail.getModel();
            final String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            final Multipart multipart = new MimeMultipart("related");
            final MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(html, "text/html");

            multipart.addBodyPart(mimeBodyPart);

            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setFrom(mail.getFrom());

            mimeMessageHelper.setTo(mail.getTo().toArray(new String[0]));

            if (!CollectionUtils.isEmpty(mail.getCc())) {
                mimeMessageHelper.setCc(mail.getCc().toArray(new String[0]));
            }

            mimeMessageHelper.setText(html, true);
            mimeMessage.setContent(multipart);

            emailSender.send(mimeMessage);
        } catch (MessagingException | IOException | TemplateException exception) {
            log.error("Error Sending Email : {}", exception.getMessage());
        }
    }

}
