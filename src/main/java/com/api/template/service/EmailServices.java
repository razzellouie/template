package com.api.template.service;

import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailServices {

    @Autowired
    private JavaMailSender sender;
    
    private MimeMessagePreparator preparator;
    
    @Autowired
    public EmailServices(JavaMailSender sender) {
        this.sender = sender;
    }
    
    @Value("${spring.mail.default.from}")
    private String from;
    
    public void setPreparator(Map<String, Object> map) {
        this.preparator = message -> {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED, "UTF-8");
            helper.setFrom(from);
            helper.setTo(InternetAddress.parse(map.get("TO").toString()));
            helper.setSubject(map.get("SUBJECT").toString());
            helper.setText(map.get("BODY").toString(), true);
        };
        this.sentMail();
    }
    
    public MimeMessagePreparator getPreparator() {
        return this.preparator;
    }
    
    public void sentMail() {
        try {
            sender.send(this.getPreparator());
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
