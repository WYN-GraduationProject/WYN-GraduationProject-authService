package com.constantineqaq.base.listener;

import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class MailMessageListener {
    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    @RabbitListener(queues = "mail")
    public void receiveMessage(Map<String, Object> message) {
        System.out.println("Received message from mail queue: " + message);

        String type = (String) message.get("type");
        String email = (String) message.get("email");
        Integer code = (Integer) message.get("code");

        String content = generateHtmlContent(type, code);
        sendEmail(email, content);
    }

    private String generateHtmlContent(String type, Integer code) {
        // HTML内容，内联CSS样式用于美化

        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<style>" +
                "body {font-family: Arial, sans-serif; margin: 20px; padding: 0; color: #333;}" +
                ".container {background-color: #f9f9f9; border: 1px solid #ddd; padding: 20px; text-align: center;}" +
                ".header {font-size: 24px; margin-bottom: 20px;}" +
                ".code {font-size: 20px; color: #007BFF; font-weight: bold; margin: 20px 0;}" +
                ".footer {font-size: 14px; color: #666;}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<div class=\"header\">您的" + type + "验证码</div>" +
                "<p>感谢您使用我们的服务，请在下方输入您的验证码：</p>" +
                "<div class=\"code\">" + code + "</div>" +
                "<p>请注意，此验证码在10分钟内有效。</p>" +
                "<div class=\"footer\">如果您没有请求此验证码，请忽略此邮件。</div>" +
                "</div>" +
                "</body>" +
                "</html>";
    }

    private void sendEmail(String email, String content) {
        // 实现发送电子邮件的逻辑
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(email);
        message.setSubject("您的邮箱验证码");
        message.setText(content);
        javaMailSender.send(message);
    }
}
