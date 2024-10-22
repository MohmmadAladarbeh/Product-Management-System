package org.example.productportal.config;

import jakarta.faces.webapp.FacesServlet;
import jakarta.servlet.Servlet;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class JSFConfig {
    @Bean
    public ServletRegistrationBean<Servlet> facesServlet() {
        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

    // Registering a default listener (optional, add if necessary)
//    @Bean
//    public ServletListenerRegistrationBean<javax.faces.event.PhaseListener> jsfPhaseListener() {
//        return new ServletListenerRegistrationBean<>(new YourCustomPhaseListener()); // Replace with your actual listener if needed
//    }
}
