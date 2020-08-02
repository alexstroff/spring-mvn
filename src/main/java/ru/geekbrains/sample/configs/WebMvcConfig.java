package ru.geekbrains.sample.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import ru.geekbrains.sample.dto.Student;
import ru.geekbrains.sample.repository.StudentsRepository;

@Configuration
@EnableWebMvc
@ComponentScan("ru.geekbrains.sample")
public class WebMvcConfig implements WebMvcConfigurer {

   private Long id;
   private String name;
   private int score;

   @Bean
   public SpringResourceTemplateResolver templateResolver() {
      SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
      templateResolver.setPrefix("/WEB-INF/templates/");
      templateResolver.setSuffix(".html");
      return templateResolver;
   }

   @Bean
   public SpringTemplateEngine templateEngine() {
      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
      templateEngine.setTemplateResolver(templateResolver());
      templateEngine.setEnableSpringELCompiler(true);
      return templateEngine;
   }
//   @Bean
//   @Scope(value = "prototype")
//   public Student student(){
//      Student student = new Student();
//      return student;
//   }


   @Bean
   public StudentsRepository students(){
      StudentsRepository students = new StudentsRepository();
      return  students;
   }


   @Override
   public void configureViewResolvers(ViewResolverRegistry registry) {
      ThymeleafViewResolver resolver = new ThymeleafViewResolver();
      resolver.setCharacterEncoding("UTF-8");
      resolver.setTemplateEngine(templateEngine());
      registry.viewResolver(resolver);
   }

}