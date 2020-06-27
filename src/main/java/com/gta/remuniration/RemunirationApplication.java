package com.gta.remuniration;
import com.gta.remuniration.entity.Role;
import com.gta.remuniration.entity.Salarie;
import com.gta.remuniration.entity.User;
import com.gta.remuniration.entity.user_role;
import com.gta.remuniration.repository.RoleRepository;
import com.gta.remuniration.repository.SalarieRepository;
import com.gta.remuniration.repository.UserRepository;
import com.gta.remuniration.repository.user_roleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@EnableAsync

@EnableScheduling


public class RemunirationApplication implements CommandLineRunner {
    @Autowired
    SalarieRepository salarieRepository ;
    @Autowired
    UserRepository userRepository  ;
    @Autowired
    RoleRepository roleRepository ;
    @Autowired
    user_roleRepository user_roleRepository ;
    public static void main(String[] args) {
        SpringApplication.run(RemunirationApplication.class, args);
    }
    @Override
    public void run(String... arg0)throws Exception{



       /* Salarie salarie = new Salarie("Mojid","fati","fati@gmail.com","0617328612" );
        salarieRepository.save(salarie);
        User user = new User("Mojid","123456");
        userRepository.save(user);
        Role role =new Role("1","Admin");
        roleRepository.save(role);
        user_role userRole = new user_role(user,role);
        user_roleRepository.save(userRole);*/


    }

    @Bean

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD");
                registry.addMapping("/**").allowedHeaders(" Origin"," Accept"," X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers", "autorisation ");
                registry.addMapping("/**").allowedOrigins("*");
                registry.addMapping("/**").exposedHeaders(" Access-Control-Allow-Origin"," Access-Control-Allow-Credentials"," autorisation ");

            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}


