package gov.rw.brd.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/dashboard").setViewName("dashboard");
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/add-account").setViewName("add-account");
		registry.addViewController("/view-kigali-branch").setViewName("view-kigali-branch");
		registry.addViewController("/edit-file-page").setViewName("edit-file-page");
		registry.addViewController("/view-account-files").setViewName("view-account-files");
		registry.addViewController("/login").setViewName("login");
	}

}