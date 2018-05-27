package net.wendal.nutzbook.service;

import org.apache.commons.mail.HtmlEmail;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

@IocBean(name="emailService")
public class EmailServiceImpl implements EmailService {

    private static final Log log = Logs.get();

    @Inject("refer:$ioc")
    protected Ioc ioc;

    public boolean send(String to, String subject, String html) {
        try {
            log.info("开始发送邮件......");
            HtmlEmail email = ioc.get(HtmlEmail.class);
            email.setSubject(subject);
            email.setHtmlMsg(html);
            email.addTo(to);
            email.buildMimeMessage();
            email.sendMimeMessage();
            return true;
        } catch (Throwable e) {
            log.info("发送邮件失败......");
            return false;
        }
    }
}