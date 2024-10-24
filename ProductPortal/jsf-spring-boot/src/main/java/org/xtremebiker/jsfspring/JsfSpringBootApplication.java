package org.xtremebiker.jsfspring;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ServletContextAware;
import org.xtremebiker.jsfspring.view.ViewScope;

import com.google.common.collect.ImmutableMap;

@SpringBootApplication
public class JsfSpringBootApplication  {
	public static void main(String[] args) {
		SpringApplication.run(JsfSpringBootApplication.class, args);
	}
}
