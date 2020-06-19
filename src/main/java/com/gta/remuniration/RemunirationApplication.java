package com.gta.remuniration;

import com.gta.remuniration.entity.BudgetDepartement;
import com.gta.remuniration.exception.NotFoundException;
import com.gta.remuniration.repository.*;
import com.gta.remuniration.service.BudgetDepartService;
import com.gta.remuniration.service.BudgetEquipeService;
import com.gta.remuniration.service.DepartementService;
import com.gta.remuniration.service.EtatDemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
    private DepartementService departementService;
    @Autowired
    private BudgetDepartementRepository budgetDepartementRepository;
    @Autowired
    private BudgetEquipeService budgetEquipeService;
    @Autowired
    private BudgetDepartService budgetDepartService;
    @Autowired
    private EtatDemandeService etatDemandeService;
    public RemunirationApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(RemunirationApplication.class, args);
    }
    @Override
    public void run(String... arg0)throws Exception{

       /*
        Salarie salarie = new Salarie("Mojid","fati","fati@gmail.com","0617328612" );
        salarieRepository.save(salarie);
        User user = new User("Mojid","123456");
        userRepository.save(user);
        Role role =new Role("1","Admin");
        roleRepository.save(role);
        user_role userRole = new user_role(user,role);
        user_roleRepository.save(userRole);
        */
        Long l=new Long(72);
       // budgetEquipeService.create("200000","5000000","1");
       //budgetEquipeService.getByID(l);
    }


    @Bean

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



    }


