package net.etfbl.ip.vm.configurations;

import net.etfbl.ip.vm.util.AuthorizationInterceptor;
import net.etfbl.ip.vm.util.Logger;
import net.etfbl.ip.vm.util.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

@Configuration
public class MyAppConfiguration implements WebMvcConfigurer {
    @Bean
    public ServletListenerRegistrationBean<SessionListener> sessionListener(){
        ServletListenerRegistrationBean<SessionListener> listenerBean = new ServletListenerRegistrationBean<>();
        listenerBean.setListener(new SessionListener());
        return listenerBean;
    }

    @Autowired
    private Logger logger;
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logger);
        registry.addInterceptor(authorizationInterceptor);
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("virtualmuseum.ip@gmail.com");
        mailSender.setPassword("milica113216");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

}
